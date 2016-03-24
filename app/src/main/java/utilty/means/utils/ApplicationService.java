package utilty.means.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * @author mahesh.baraskar
 *
 */
public class ApplicationService extends AsyncTask<String, Void, Result> // implements
// LocationListener
{

	IApplicationService connectionService;

	Context context;

	ProgressDialog progressDailog;

	String title;
	
	private boolean isRunning;

	private final String LOG_TAG = "ApplicationService";

	private boolean isLoader=true;

	public ApplicationService(Context context, String title, IApplicationService connectionService)
	{

		this.context = context;
		this.connectionService = connectionService;
		this.title = title;

	}

	public boolean isLoader() {
		return isLoader;
	}

	public void setIsLoader(boolean isLoader) {
		this.isLoader = isLoader;
	}

	@Override
	protected void onPreExecute()
	{

		if (isLoader()) {
			progressDailog = new ProgressDialog(context);
			progressDailog.setMessage(title);
			progressDailog.show();
			progressDailog.setCancelable(false);
		}
		super.onPreExecute();
	}

	@Override
	protected Result doInBackground(String... params)
	{

		try
		{
			setRunning(true);
			return connectionService.execute();
		} finally
		{
			setRunning(false);
		}

	}

	@Override
	protected void onPostExecute(Result result)
	{

		if (context != null)
		{
			connectionService.postExecute(result);
			if (isLoader()) {
				progressDailog.dismiss();
			}
		}
		super.onPostExecute(result);
	}

	
	public boolean isRunning()
	{
	
		return isRunning;
	}

	
	public void setRunning(boolean isRunning)
	{
	
		this.isRunning = isRunning;
	}

	

}
