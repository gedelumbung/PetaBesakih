package dlmbg.pckg.petabesakih;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailPeta extends Activity {

	ArrayList<HashMap<String, String>> dataMap = new ArrayList<HashMap<String, String>>();
	private ProgressDialog pDialog;
	JSONParser jParser = new JSONParser();
	String link_url = "";
	JSONArray str_json = null;
	Koneksi lo_Koneksi = new Koneksi();
	String isi = lo_Koneksi.isi_koneksi();
	
	TextView judul_set, keterangan_set;
	ImageView gambar_set;

	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_peta);

        keterangan_set = (TextView) findViewById(R.id.keterangan);
        judul_set = (TextView) findViewById(R.id.judul);
        gambar_set = (ImageView) findViewById(R.id.gambar);

        Bundle b = getIntent().getExtras();
		String judul = b.getString("judul");
		
    	link_url = isi + "detail.php?judul="+judul;
        new getListInfo().execute();
	}
	
	class getListInfo extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(DetailPeta.this);
			pDialog.setMessage("Menghubungkan ke server...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {

			JSONObject json = jParser.AmbilJson(link_url);

			try {
				str_json = json.getJSONArray("info");
				
				for(int i = 0; i < str_json.length(); i++)
				{
					JSONObject ar = str_json.getJSONObject(i);
					HashMap<String, String> map = new HashMap<String, String>();

					map.put("judul", ar.getString("judul"));
					map.put("koordinat_lang",  ar.getString("koordinat_lang"));
					map.put("koordinat_lat",  ar.getString("koordinat_lat"));
					map.put("keterangan",  ar.getString("keterangan"));
					map.put("gambar",  ar.getString("gambar"));

					dataMap.add(map);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			runOnUiThread(new Runnable() {
                public void run() {
                	
                    for (int i = 0; i < dataMap.size(); i++)
                    {
                    	HashMap<String, String> map = new HashMap<String, String>();
                    	map = dataMap.get(i);
                    	judul_set.setText(map.get("judul"));
                    	keterangan_set.setText(map.get("keterangan"));

                    	new DownloadImagesTask().execute(isi+"gambar/"+map.get("gambar"));
                    }
                }
            });
		}

	}
	public class DownloadImagesTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... urls) {
		    return download_Image(urls[0]);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			gambar_set.setImageBitmap(result);
		}


		private Bitmap download_Image(String url) {
		    Bitmap bm = null;
		    try {
		        URL aURL = new URL(url);
		        URLConnection conn = aURL.openConnection();
		        conn.connect();
		        InputStream is = conn.getInputStream();
		        BufferedInputStream bis = new BufferedInputStream(is);
		        bm = BitmapFactory.decodeStream(bis);
		        bis.close();
		        is.close();
		    } catch (IOException e) {
		        Log.e("Hub","Error getting the image from server : " + e.getMessage().toString());
		    } 
		    return bm;
		}
	}
}
