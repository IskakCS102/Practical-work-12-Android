package kz.talipovsn.browser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.webkit.*;
import android.widget.*;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;
    EditText urlText;
    ImageButton backButton, forwardButton, refreshButton, loadButton, forwardSearchButton;
    FloatingActionButton schedule, profile, forum, chat, logout;
    ImageButton homeButton;
    RelativeLayout mainToolLayout, searchToolLayout;
    MyViewClient myViewClient = new MyViewClient();

    final int MAX_100 = 100;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlText = findViewById(R.id.urlText);
        loadButton = findViewById(R.id.loadButton);
        backButton = findViewById(R.id.backButton);
        refreshButton = findViewById(R.id.refreshButton);
        forwardButton = findViewById(R.id.forwardButton);
        homeButton = findViewById(R.id.homeButton);
        schedule = findViewById(R.id.schedule);
        profile = findViewById(R.id.profile);
        forum = findViewById(R.id.forum);
        chat = findViewById(R.id.chat);
        logout = findViewById(R.id.logout);
        mainToolLayout = findViewById(R.id.mainToolLayout);
        searchToolLayout = findViewById(R.id.searchToolLayout);
        progressBar = findViewById(R.id.progressBar);
        webView = findViewById(R.id.webView);

        searchToolLayout.setVisibility(View.GONE);
        progressBar.setMax(MAX_100);
        progressBar.setVisibility(View.GONE);

        urlText.setFocusable(false);
        urlText.setFocusableInTouchMode(false);
        urlText.setTextIsSelectable(true);
        urlText.setHighlightColor(Color.MAGENTA);

        webView.requestFocus();

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        //webView.getSettings().setAppCacheEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setBackgroundColor(Color.WHITE);

        webView.setWebViewClient(myViewClient);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);

        // Обработчик отображения загрузки страниц
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setProgress(progress);
                if (progress < MAX_100 && progressBar.getVisibility() == ProgressBar.GONE) {
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                }
                if (progress == MAX_100) {
                    progressBar.setVisibility(ProgressBar.GONE);
                }
            }
        });

        // Добавление меню с возможностью загрузки картинок
        registerForContextMenu(webView);

        // Восстановление состояния просмотра (страницы) в webView
        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState);
        } else {
            webView.loadUrl(getString(R.string.home_page));
        }

        // Обработчик кнопки загрузки страницы
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Скрываем клавиатуру
                hideSoftInput();
                // Загружаем страниу в webView
                webView.loadUrl(urlText.getText().toString());
            }
        });

        // Обработчик кнопки Назад
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webView.canGoBack()) {
                    webView.goBack();
                }
            }
        });

        // Обработчик кнопки Вперед
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webView.canGoForward()) {
                    webView.goForward();
                }
            }
        });

        // Обработчик кнопки Обновить
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.reload();
            }
        });

        // Обработчик кнопки Домой
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Скрываем клавиатуру
                hideSoftInput();
                // Загружаем страниу в webView
                webView.loadUrl(getString(R.string.home_page));
                // Показываем адрес домашней страницы
                urlText.setText(getString(R.string.home_page));
            }
        });

        // Обработчик кнопки Поиск
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Скрываем клавиатуру
                hideSoftInput();
                // Загружаем страниу в webView
                webView.loadUrl(getString(R.string.calendar));
                // Показываем адрес домашней страницы
                urlText.setText(getString(R.string.calendar));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Скрываем клавиатуру
                hideSoftInput();
                // Загружаем страниу в webView
                webView.loadUrl(getString(R.string.profile));
                // Показываем адрес домашней страницы
                urlText.setText(getString(R.string.profile));
            }
        });

        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Скрываем клавиатуру
                hideSoftInput();
                // Загружаем страниу в webView
                webView.loadUrl(getString(R.string.forum));
                // Показываем адрес домашней страницы
                urlText.setText(getString(R.string.forum));
            }
        });


        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Скрываем клавиатуру
                hideSoftInput();
                // Загружаем страниу в webView
                webView.loadUrl(getString(R.string.chat));
                // Показываем адрес домашней страницы
                urlText.setText(getString(R.string.chat));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Скрываем клавиатуру
                hideSoftInput();
                // Загружаем страниу в webView
                webView.loadUrl(getString(R.string.logout));
                // Показываем адрес домашней страницы
                urlText.setText(getString(R.string.logout));
            }
        });



    }

    private void hideSoftInput() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
        }
    }

    // Класс собственного загрузчика html
    public class MyViewClient extends WebViewClient {
        // Переопределение метода загрузки страницы
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            urlText.setText(url);
            view.loadUrl(url);
            CookieManager.getInstance().setAcceptCookie(true);
            return true;
        }

        // Переопределение метода окончания загрузки страницы
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            urlText.setText(webView.getUrl());
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageStarted(view, url, favicon);
        }

    }

    // Обработчик системной кнопки назад
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    // Обработчик записи состояния активности перед поворотом
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

}
