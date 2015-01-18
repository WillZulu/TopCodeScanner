package com.server;

/*
 * Copyright (c) 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import android.R;
import android.content.Context;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.*;
import com.google.api.client.json.JsonFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Collections;

import org.json.simple.*;
import org.json.simple.parser.*;
//
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.model.StorageObject;

/**
 * @author Yaniv Inbar
 */
public class GCS
{
	//
	private static final String SERVICE_ACCOUNT_EMAIL = "488132785016-vps63uuu7bar77tf1ejhjc471m8tgv1e@developer.gserviceaccount.com";
	private static String tag = "HI";
	private static final String BUCKET_NAME = "thoughtstem_minecraft";
	private static final String STORAGE_SCOPE = "https://www.googleapis.com/auth/devstorage.read_write";
	private static HttpTransport httpTransport;
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static GoogleCredential creds;
	private static HttpRequestFactory requestFactory;
	private static Storage storage;

	public static void initGCS()
	{
		try
		{
//			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			httpTransport = AndroidHttp.newCompatibleTransport();
			// Build service account credential.
			creds = new GoogleCredential.Builder().setTransport(httpTransport).setJsonFactory(JSON_FACTORY).setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
			.setServiceAccountScopes(Collections.singleton(STORAGE_SCOPE)).setServiceAccountPrivateKeyFromP12File(new File("/sdcard/4dc3c5ae7e42a946a5826519451c8b6b21cb566a-privatekey.p12")).build();
			requestFactory = httpTransport.createRequestFactory(creds);
			storage = new Storage(httpTransport, JSON_FACTORY, requestFactory.getInitializer());
		} catch (Exception e)
		{
			Log.i("HI", "GCS INIT ERROR. PERHAPS YOU'RE MISSING KEY.P12?");
			Log.e(tag, "Exception: " + e.getMessage());
		}
	}

	public static CloudObject find(String bucket, String key)
	{
		try
		{
			Storage.Objects.Get getObject = storage.objects().get(bucket, key);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			getObject.executeMediaAndDownloadTo(out);
			String value = out.toString();
			JSONObject obj;
			JSONParser p = new JSONParser();
			obj = (JSONObject) p.parse(value);

			return new CloudObject(getObject.getObject(), obj);

		} catch (com.google.api.client.http.HttpResponseException e)
		{
//			Log.e(tag, "Exception: " + e.getMessage());
			e.printStackTrace();
			return null;
		} catch (Exception e)
		{
//			Log.e(tag, "Exception: " + e.printStackTrace());
			e.printStackTrace();
			return null;
		}
	}

	public static String getDefaultBucketName()
	{
		return BUCKET_NAME;
	}

	public static void put(String bucket, CloudObject obj)
	{
		try
		{

			String k = obj.getName();
			String v = obj.getValue().toJSONString();

			String URI = "https://storage.googleapis.com/" + bucket;

			InputStream inputStream = new ByteArrayInputStream(v.getBytes("UTF-8"));
			long byteCount = v.getBytes().length;

			InputStreamContent mediaContent = new InputStreamContent("application/json", inputStream);

			// Knowing the stream length allows server-side optimization, and client-side progress
			// reporting with a MediaHttpUploaderProgressListener.
			mediaContent.setLength(byteCount);

			StorageObject objectMetadata = null;

			Storage.Objects.Insert insertObject = storage.objects().insert(BUCKET_NAME, objectMetadata, mediaContent);

			insertObject.setName(k);

			// If you don't provide metadata, you will have specify the object
			// name by parameter. You will probably also want to ensure that your
			// default object ACLs (a bucket property) are set appropriately:
			// https://developers.google.com/storage/docs/json_api/v1/buckets#defaultObjectAcl

			// For small files, you may wish to call setDirectUploadEnabled(true), to
			// reduce the number of HTTP requests made to the server.
			if (mediaContent.getLength() > 0 && mediaContent.getLength() <= 5 * 1000 * 1000 /*
																							 * 2
																							 * MB
																							 */)
			{
				insertObject.getMediaHttpUploader().setDirectUploadEnabled(true);
			}

			insertObject.execute();

		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}

/*
 * METADATA EX if (useCustomMetadata) { // If you have custom settings for
 * metadata on the object you want to set // then you can allocate a
 * StorageObject and set the values here. You can // leave out setBucket(),
 * since the bucket is in the insert command's // parameters. objectMetadata =
 * new StorageObject() .setName("myobject") .setMetadata(ImmutableMap.of("key1",
 * "value1", "key2", "value2")) .setAcl(ImmutableList.of( new
 * ObjectAccessControl().setEntity("domain-example.com").setRole("READER"), new
 * ObjectAccessControl
 * ().setEntity("user-administrator@example.com").setRole("OWNER") ))
 * .setContentDisposition("attachment"); }
 */