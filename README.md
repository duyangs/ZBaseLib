<img src="image/dybaselib.jpg"/>

# BaseLib
Android App 项目 base 引用依赖库
==
<br>[图片展示](#1)</br>
<br>[How to](#2)</br>
<br>[功能简述](#3)</br>
  <br>&nbsp;&nbsp;&nbsp;&nbsp;[1.BaseActivityManager](#3.1)</br>
  <br>&nbsp;&nbsp;&nbsp;&nbsp;[2.BaseToolbar](#3.2)</br>
  <br>&nbsp;&nbsp;&nbsp;&nbsp;[3.BaseActivity](#3.3)</br>
  <br>&nbsp;&nbsp;&nbsp;&nbsp;[4.BaseFragment](#3.4)</br>
  <br>&nbsp;&nbsp;&nbsp;&nbsp;[5.ToastUtil](#3.5)</br>
  <br>&nbsp;&nbsp;&nbsp;&nbsp;[6.TextSpannableUtil](#3.6)</br>
  <br>&nbsp;&nbsp;&nbsp;&nbsp;[7.DateUtil](#3.7)</br> 
  <br>&nbsp;&nbsp;&nbsp;&nbsp;[8.LogUtil](#3.8)</br> 
***

<h2 id= '1'>图片展示</h2>

<div >   
<img src="image/BaseToolbar.jpg" width = "150" height = "300" alt="BaseToolBar" align=center />

<img src="image/TextSpannableUtil.jpg" width = "150" height = "300" alt="TextSpannableUtil" align=center />

<img src="image/ToastUtil.jpg" width = "150" height = "300" alt="ToastUtil" align=center />
</div>


<h2 id = '2'>How to</h2>

***
<pre><code>
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
    
Add the dependency

	dependencies {
		compile 'com.github.duyangs:BaseLib:v0.0.4'
	}
    
</code></pre>

***
<h2 id='3'>功能简述</h2>
***
<h3 id = '3.1'>1.BaseActivityManager --- 暂不支持继承</h3>

    Activity栈管理工具类(包涵方法如下)
    
    BaseActivityManager.getInstance().addActivity(activity);  添加指定Activity到堆栈
    
    BaseActivityManager.getInstance().currentActivity();  获取当前Activity （栈顶的activity）
    
    BaseActivityManager.getInstance().applicationContext();  获取站点Activity的ApplicationContext
    
    BaseActivityManager.getInstance().deleteActivity();  删除栈顶Activity
    
    BaseActivityManager.getInstance().deleteActivity(activity); 删除指定Activity
    
    BaseActivityManager.getInstance().deleteActivity(class); 删除指定Class的Activity
    
    BaseActivityManager.getInstance().deleteAllActivity(); 清空栈
    
    BaseActivityManager.getInstance().deleteOthersActivity(index); 保留栈底的 index 个Activity 其余删除
    
    BaseActivityManager.getInstance().getActivity(class); 获取指定类名的Activity
    
    BaseActivityManager.getInstance().AppExit();  退出应用程序



<h3 id = '3.2'>2.BaseToolbar --- 可直接调用Toolbar的封装类，暂不支持继承</h3>

    使用时需在布局文件中引用base_title.xml
    
    设置标题
    setTitleText(String); 
    
    setTitleText(int)
    
    隐藏返回键
    hideNavigationIcon();
    
    将navigationOnClick 和 onMenuItemClick 封装成接口  方便在BaseActivity的子类中直接实现对ToolBar的点击监听操作
    public interface OnClickListener{
        void navigationOnClick(View v);

        boolean onMenuItemClick(MenuItem item);
    }
    
    获取ToolBar 方便自己实现其他功能 
    getmToolbar() 
    
    
<h3 id = '3.3'>3.BaseActivity --- Activity基类 支持继承 （ 已经调用了BaseActivityManager 的添加和删除 ）</h3>
<pre>
<code>
    四个抽象方法
    
      绑定布局
      @return 布局资源id
    protected abstract int bindLayout();
    
     初始化intent参数
     @param params
    protected abstract void initParams(Bundle params);

     初始化控件
    protected abstract void initView();

     初始化数据
    protected abstract void initData();
    
    子类可调用方法
    
    initBaseToolbar()  初始化BaseToolbar  表明使用baseTitle
    
    getBaseToolBar()  获取mBaseToolbar  方便子类直接使用baseToolbar中的方法 减少BaseActivity中方法数

    页面跳转
    startActivity(Class<?> clz)  
    
    startActivity(Class<?> clz, Bundle bundle)
    
    startActivityForResult(Class<?> cls, Bundle bundle,int requestCode)
    
    findById(int resId)；绑定控件
    
    toast(Object msg) 该处为居中短显示
</code>
</pre>


<h3 id = '3.4'>4.BaseFragment --- fragment 基类 支持继承</h3>

<pre><code>
分装方法类似于BaseActivity 详细可查看源码
</code></pre>


    
<h3 id = '3.5'>5.ToastUtil --- Toast封装工具类</h3>

     居上显示 短时间
     msg 显示内容
    showShortTop(Object msg)
    
     居中显示 短时间
     msg 显示内容
    showShortCenter(Object msg)
    
     居下显示 短时间
     msg 显示内容
    showShortBottom(Object msg)
     居上显示 长时间
     msg 显示内容
    showLongTop(Object msg)
    
     居中显示 长时间
     msg 显示内容
    showLongCenter(Object msg)

     居下显示 长时间
     @param msg 显示内容
    showLongBottom(Object msg)
    
     取消toast
    cancelToast()


<h3 id = '3.6'>6.TextSpannableUtil --- TextSpannable 工具类 不支持继承 直接调用</h3>

    textView1.setText(new TextSpannableUtil()
                    .addText(new TextSpannableUtil.TextStyle.Builder().setText("正常\n")
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder().setText("字号大\n")
                            .setAbsoluteSizeSpSpan(this, 25)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder().setText("前景色\n")
                            .setTextColorSpan(Color.RED)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder().setText("背景色\n")
                            .setBackgroundColorSpan(Color.BLUE)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder().setText("正常\n")
                            .setStyleSpan(TextSpannableUtil.TextStyle.Style.NORMAL)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder().setText("加粗\n")
                            .setStyleSpan(TextSpannableUtil.TextStyle.Style.BOLD)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder().setText("斜体\n")
                            .setStyleSpan(TextSpannableUtil.TextStyle.Style.ITALIC)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder().setText("粗斜体\n")
                            .setStyleSpan(TextSpannableUtil.TextStyle.Style.BOLD_ITALIC)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("下划线\n")
                            .setUnderlineSpan(true)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("删除线\n")
                            .setStrikethroughSpan(true)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("上标\n")
                            .setTextColorSpan(Color.RED)
                            .setSuperscriptsAndSubscripts(TextSpannableUtil.TextStyle.SuperscriptsAndSubscripts.SUPERSCRIPT)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("下标\n")
                            .setSuperscriptsAndSubscripts(TextSpannableUtil.TextStyle.SuperscriptsAndSubscripts.SUBSCRIPT)
                            .build())
                    .addText("文字\n", new TextSpannableUtil.TextStyle.Builder()
                            .setStyleSpan(TextSpannableUtil.TextStyle.Style.BOLD)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("缩进\n")
                            .setLeadingMargin(100, 50)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("引用\n")
                            .setQuoteColor(Color.RED)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("列表项\n")
                            .setBullet(100, Color.BLACK)
                            .setAbsoluteSizeDipSpan(30)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("2倍字体\n")
                            .setProportion(2)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("横向2倍字体\n")
                            .setXProportion(2)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("monospace font\n")
                            .setFontFamily("monospace")
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("serif font\n")
                            .setFontFamily("serif")
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("sans-serif font\n")
                            .setFontFamily("sans-serif")
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("测试正常对齐\n")
                            .setAlign(Layout.Alignment.ALIGN_NORMAL)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("测试居中对齐\n")
                            .setAlign(Layout.Alignment.ALIGN_CENTER)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("测试相反对齐\n")
                            .setAlign(Layout.Alignment.ALIGN_OPPOSITE)
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("BaseLib\n")
                            .setURL(textView1, "https://github.com/duyangs/BaseLib")
                            .build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("点击事件\n")
                            .setOnClickListener(textView1, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ToastUtil.showShortCenter("点击事件");
                                }
                            }).build())
                    .addText(new TextSpannableUtil.TextStyle.Builder()
                            .setText("图片")
                            .setImage(this, R.mipmap.ic_launcher, DynamicDrawableSpan.ALIGN_BASELINE)
                            .build())
                    .toSpannableString());
		    
<h3 id = '3.7'>7.DateUtil -- 日期时间工具类</h3>

<pre><code>
详细可查看源码
</code></pre>

<h3 id = '3.7'>7.LogUtil -- 日志工具类</h3>

<pre><code>
 	/**
         * 初始化 LogUtil 并设置是否显示日志
         */
        LogUtil.Builder logBuilder = new LogUtil.Builder().setLogOn(true);
	
	setLogOn(true)  设置显示日志
	详细可查看源码
</code></pre>
