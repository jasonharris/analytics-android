package io.segment.android.test;

import io.segment.android.Analytics;
import io.segment.android.Configuration;
import io.segment.android.Options;

import org.junit.Assert;
import org.junit.Test;

import android.content.Context;
import android.content.res.Resources;
import android.test.ActivityTestCase;

public class ConfigurationTest extends ActivityTestCase {

	@Test
	public void testSecret() {
	    Context context = getInstrumentation().getContext();
		Resources resources = context.getResources();
		String secret = Configuration.getSecret(context);
		Assert.assertEquals(resources.getString(R.string.analytics_secret), secret);
	}
	
	@Test
	public void testOptions() {
		Context context = getInstrumentation().getContext();
		Options options = Configuration.getOptions(context);
		testOptions(context, options);
	}
	
	@Test
	public void testInitialization() {
	    Context context = getInstrumentation().getContext();
	    if (Analytics.isInitialized()) Analytics.close();
		Analytics.initialize(context);
		Options options = Analytics.getOptions();
		testOptions(context, options);
		Analytics.close();
	}
	
	private void testOptions(Context context, Options options) {
		
		Resources resources = context.getResources();
		
		Assert.assertEquals(resources.getInteger(R.integer.analytics_flush_after), options.getFlushAfter());
		Assert.assertEquals(resources.getInteger(R.integer.analytics_flush_at), options.getFlushAt());
		Assert.assertEquals(resources.getInteger(R.integer.analytics_max_queue_size), options.getMaxQueueSize());
		Assert.assertEquals(resources.getInteger(R.integer.analytics_settings_cache_expiry), options.getSettingsCacheExpiry());

		Assert.assertEquals(resources.getString(R.string.analytics_host), options.getHost());
		Assert.assertEquals(Boolean.parseBoolean(resources.getString(R.string.analytics_debug)), options.isDebug());
	}
	
}
