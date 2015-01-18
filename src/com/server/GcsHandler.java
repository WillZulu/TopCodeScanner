package com.server;

/**
 * User: links Date: 5/1/14
 * 
 * Grabs Bukkit.getLogger() and routes LogRecords to GCS by default every 5
 * seconds.
 * 
 * Stores to gc://some-thoughtstem-bucket-name/tsBukkitLog.(new
 * Date()).toString() by default
 * 
 * GCS Objects are immutable, therefore we have to write the entire log
 * everytime.
 */
public class GcsHandler implements Runnable
{

	@Override
	public void run()
	{

	}
}

/*
 * private static String logString = "";
 * 
 * private static final int SLEEP_TIME = 5000; //5 seconds
 * 
 * private static final String bucketName = "thoughtstem_minecraft";
 * 
 * private static GcsFilename GcsObjectPath;
 * 
 * private final GcsService gcsService =
 * GcsServiceFactory.createGcsService(RetryParams.getDefaultInstance());
 * 
 * private static GcsOutputChannel outputChannel;
 * 
 * 
 * public GcsHandler(Server server) {
 * 
 * server.getLogger().addHandler(this);
 * 
 * //String objectName = "tsBukkitLog."+(new Date()).toString(); String
 * objectName = "test"; GcsObjectPath = new GcsFilename(bucketName, objectName);
 * 
 * try { outputChannel = gcsService.createOrReplace(GcsObjectPath,
 * GcsFileOptions.getDefaultInstance()); } catch (IOException e) {
 * e.printStackTrace(); }
 * 
 * System.out.println("Logging to "+outputChannel.toString());
 * 
 * }
 * 
 * public void run() { while (true) {
 * 
 * try { Thread.sleep(SLEEP_TIME); //wait 5 seconds logToGCS(); } catch
 * (InterruptedException e) { e.printStackTrace(); } } }
 * 
 * 
 * /** logToGCS
 * 
 * writes log to Google Cloud Storage
 *//*
	 * public void logToGCS() { try {
	 * 
	 * @SuppressWarnings("resource") ObjectOutputStream oout = new
	 * ObjectOutputStream(Channels.newOutputStream(outputChannel));
	 * oout.writeObject(logString); oout.close();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * public void publish(LogRecord lr) { logString += lr.toString() + "\n"; }
	 * 
	 * public void flush() { logString = ""; }
	 * 
	 * public void close() { flush(); try { outputChannel.close(); } catch
	 * (Exception e) { e.printStackTrace(); } }
	 * 
	 * }
	 */