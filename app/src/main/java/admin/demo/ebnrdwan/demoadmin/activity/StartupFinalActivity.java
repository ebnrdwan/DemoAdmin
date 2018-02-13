package  admin.demo.ebnrdwan.demoadmin.activity;

import  admin.demo.ebnrdwan.demoadmin.DBManager;
import  admin.demo.ebnrdwan.demoadmin.R;
import  admin.demo.ebnrdwan.demoadmin.SettingsManager;
import  admin.demo.ebnrdwan.demoadmin.services.AppService;
import com.stericson.RootTools.RootTools;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import static android.app.AppOpsManager.MODE_ALLOWED;
import static android.app.AppOpsManager.OPSTR_GET_USAGE_STATS;

public class StartupFinalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_startup_final);
		
		CharSequence styledText = Html.fromHtml(getString(R.string.connectionSuccess));
		TextView tv = (TextView) findViewById(R.id.wizard_text);
		tv.setText(styledText);
		
		findViewById(R.id.wizard_btn_finish).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(StartupFinalActivity.this, MainMenuActivity.class));
				StartupFinalActivity.this.finish();
			}
		});
		
		new FinalPrepareTask().execute();
	}
	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	private boolean checkForPermission(Context context) {
		AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
		int mode = appOps.checkOpNoThrow(OPSTR_GET_USAGE_STATS, Process.myUid(), context.getPackageName());
		return mode == MODE_ALLOWED;
	}
	private class FinalPrepareTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			if (RootTools.isRootAvailable()) {
				RootTools.isAccessGiven();
	        }
			
			PackageManager p = getPackageManager();
	        ComponentName componentName = new ComponentName(StartupFinalActivity.this, LauncherActivity.class);
	        p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
	        
	        DBManager m = new DBManager(StartupFinalActivity.this);
	        m.getReadableDatabase().close();
	        m.close();
	        
	        SettingsManager settings = new SettingsManager(StartupFinalActivity.this);
	        settings.connected(true);
	        
	        startService(new Intent(StartupFinalActivity.this, AppService.class));
	        
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			Button btn = (Button) findViewById(R.id.wizard_btn_finish);
			btn.setText(R.string.finish);
			btn.setEnabled(true);
		}
		
	}
}
