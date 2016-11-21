package com.example.suryasolanki.cardviewjson;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.example.suryasolanki.cardviewjson.action.FOO";
    private static final String ACTION_BAZ = "com.example.suryasolanki.cardviewjson.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.suryasolanki.cardviewjson.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.suryasolanki.cardviewjson.extra.PARAM2";

    private int result= Activity.RESULT_CANCELED;
    public static final String URL="URLPath";
    public static final String FILENAME="fileName";
    public static final String FILEPAH="filePath";
    public static final String RESULT="result";
    public static final String NOTIFICATION="com.vogella.android.service.receiver";

    public MyIntentService() {
        super("MyIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }
    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
        String urlPath=intent.getStringExtra(URL);
        String fileName=intent.getStringExtra(FILENAME);

        File output=new File(Environment.getExternalStorageDirectory(),fileName);
        if(output.exists()){
            output.delete();
        }

        InputStream stream=null;
        FileOutputStream fos=null;

        try{
            URL url=new URL(urlPath);
            stream=url.openConnection().getInputStream();
            InputStreamReader reader=new InputStreamReader(stream);

            fos=new FileOutputStream(output.getPath());
            int next=-1;
            while((next=reader.read())!=-1){
                fos.write(next);
            }
            result=Activity.RESULT_OK;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(stream!=null){
                try{
                    stream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try{
                    fos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        publishResults(output.getAbsolutePath(),result);
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    public void publishResults(String outputPath,int result){
        Intent intent=new Intent(NOTIFICATION);
        intent.putExtra(FILEPAH,outputPath);
        intent.putExtra(RESULT,result);
        sendBroadcast(intent);
    }


    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
