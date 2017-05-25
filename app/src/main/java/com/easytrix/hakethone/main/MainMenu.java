package com.easytrix.hakethone.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity


        implements NavigationView.OnNavigationItemSelectedListener {
    public WebView webView;
   // ViewStub stub = (ViewStub) findViewById(R.id.stub);
  // private View viewInfl;
    //private ViewStub viewStub;
private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId()) {
                case R.id.navigation_home:
                    webView.loadUrl("file:///android_asset/generated/water/createtable.htm");
                    return true;
                case R.id.navigation_dashboard:
                    webView.loadUrl("http://byteheight.16mb.com/createtable1.php");
                    return true;
                case R.id.navigation_notifications:
                    webView.loadUrl("file:///android_asset/generated/water/update.htm");
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

      //  viewStub = (ViewStub) findViewById(stub);

       
      //  viewStub.setLayoutResource(R.layout.child1);

//        viewStub.inflate();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        webView=(WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.setWebViewClient(new myWebClient());

        webView.loadUrl("file:///android_asset/generated/water/createtable.htm");

       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      //  fab.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                   //     .setAction("Action", null).show();
        //    }
      //  });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    @SuppressLint("JavascriptInterface")
    public void open(View view){
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl("file:///android_asset/generated/water/angular_insert.html");
        webView.addJavascriptInterface(new Object()
        {
            @JavascriptInterface
            public void refuseClick()
            {
                Log.d("LOGIN::", "Clicked");
                Toast.makeText(MainMenu.this, "Login clicked", Toast.LENGTH_LONG).show();
            }
        }, "ok");

    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);
            return true;

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(MainMenu.this, SettingsActivity.class);
            startActivity(i);
            finish(); // Call once you redirect to another activity
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress)
            {
                //Make the bar disappear after URL is loaded, and changes string to Loading...
                setTitle("Loading...");
                setProgress(progress * 100); //Make the bar disappear after URL is loaded

                // Return the app name after finish loading
                if(progress == 100)
                    setTitle(R.string.app_name);
            }
        });


        if (id == R.id.nav_camera) {
            Intent i = new Intent(MainMenu.this, MainActivity.class);
            startActivity(i);

                  //   webView.loadUrl("file:///android_asset/generated/water/create_table.htm");
        } else if (id == R.id.nav_gallery) {

            webView.loadUrl("file:///android_asset/generated/water/ajaxdemo.html");

     //       stub.setLayoutResource(R.layout.child1);
       //     stub.inflate();
          // Intent i = new Intent(MainMenu.this, FbCommentsActivity.class);
              webView.loadUrl("http://byteheight.16mb.com/graphite/generated/water/show_all_tables.php");

        } else if (id == R.id.nav_slideshow) {
        //    ViewStub stub = (ViewStub)findViewById(R.id.include);
          //  stub.setLayoutResource(R.layout.content_main_menu);

            webView.loadUrl("file:///android_asset/generated/water/delete.htm");

        }

        else if (id == R.id.columns) {
            //    ViewStub stub = (ViewStub)findViewById(R.id.include);
            //  stub.setLayoutResource(R.layout.content_main_menu);
            webView.loadUrl("file:///android_asset/generated/water/angulartrial.htm");
          //  webView.loadUrl("file:///android_asset/generated/water/angular_insert.html");
            //webView.loadUrl("file:///android_asset/slidertrial15.html");

        }

        else if (id == R.id.rows) {
            //    ViewStub stub = (ViewStub)findViewById(R.id.include);
            //  stub.setLayoutResource(R.layout.content_main_menu);

            webView.loadUrl("file:///android_asset/generated/water/delete.htm");

        }

        else if (id == R.id.nav_manage) {
          //  webView.loadUrl("file:///android_asset/generated/water/update.htm");
            webView.loadUrl("file:///android_asset/generated/water/angular_insert.html");

        } else if (id == R.id.nav_share) {
            Intent i=new Intent(android.content.Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject test");
            i.putExtra(android.content.Intent.EXTRA_TEXT, "Download This App Right Now");
            startActivity(Intent.createChooser(i,"Share via"));
           // webView.loadUrl("file:///android_asset/generated/water/create.htm");
        } else if (id == R.id.nav_send) {
            webView.loadUrl("file:///android_asset/slidertrial15.html");
           //  webView.loadUrl("file:///android_asset/generated/water/insert.htm");

        }





      //  BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
