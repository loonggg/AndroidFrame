package com.loonggg.androidframedemo.ui.basic;

import android.view.View.OnClickListener;

import com.loonggg.androidframedemo.segmentcontrol.SegmentControl;
import com.loonggg.androidframedemo.ui.basic.titlebar.TitleBar;

/**
 * 提供公共的titlebar方法<BR>

 */
public interface BasicTitleBarInterface {

  /**
   * 标题左按钮对象 <BR>
   *
   * @param listener OnClickListener
   */
  void setLeftButtonListener(OnClickListener listener);

  /**
   * 标题右边第一个按钮对象 <BR>
   *
   * @param listener OnClickListener
   */
  void setRightImgBtnClickListener(OnClickListener listener);

  /**
   * 标题右边第二个按钮对象 <BR>
   *
   * @param listener OnClickListener
   */
  void setRightTexBtnListener(OnClickListener listener);

  /**
   * 设置右侧副标题<BR>
   *
   * @param subTitle 标题文字
   * @param listener 标题点击事件
   */
  void setLeftSubTitle(CharSequence subTitle, OnClickListener listener);

  /**
   * 设置左侧副标题<BR>
   *
   * @param subTitle 副标题
   */
  void setLeftSubTitle(CharSequence subTitle);

  /**
   * 获得子Activity的布局ID
   *
   * @return 子Activity的布局ID
   */
  int getLayoutId();

  /**
   * 设置左button <BR>
   *
   * @param ldrawableId 左标题图片
   * @param listener 左标题事件
   */
  void setLeftTitleButton(int ldrawableId, OnClickListener listener);

  /**
   * 设置右标题 <BR>
   *
   * @param rdrawableId 右标题图片
   * @param listener 右标题事件
   */
  void setRightImgButton(int rdrawableId, OnClickListener listener);

  /**
   * 设置右面1按钮是否可见<BR>
   *
   * @param visible 是否可见
   */
  void setRightImgButtonVisible(boolean visible);

  /**
   * 设置右标题 <BR>
   *
   * @param textId 右标题文字
   * @param listener 右标题事件
   */
  void setRightTextButton(int textId, OnClickListener listener);

  /**
   * 设置右边第二个button文字颜色<BR>
   *
   * @param colorId 颜色资源ID
   */
  void setRightTextButtonTextColor(int colorId);

  /**
   * 设置右面2按钮是否可见<BR>
   *
   * @param visible 是否可见
   */
  void setRightTextButtonVisible(boolean visible);

  /**
   * 设置中间的标题 <BR>
   *
   * @param titleId 文字资源ID
   */
  void setMiddleTitle(int titleId);

  /**
   * 设置中间的标题 <BR>
   *
   * @param titleStr 文字字符串
   */
  void setMiddleTitle(CharSequence titleStr);

  /**
   * 设置标题图片资源<BR>
   *
   * @param drawableId 图片资源ID
   */
  void setMiddleTitleDrawable(int drawableId);

  /**
   * 设置标题<BR>
   *
   * @param textTitle 中间
   * @param lDrawableId 左边图片
   * @param rImgBtnSrc 右边第一个button图片
   * @param rTextBtnSrc 右边第二个button图片
   */
  void setTitle(int textTitle, int lDrawableId, int rImgBtnSrc, int rTextBtnSrc);

  /**
   * 设置标题栏Tab页签
   *
   * @param listener 点击监听
   * @param texts 标题数组
   */
  void setTitleBarTabs(SegmentControl.OnSegmentControlClickListener listener, String... texts);

  /**
   * 初始化titleBar<BR>
   * 子类需要在该方法中调用设置按钮标题的方法，并且需要返回true才会显示标题栏
   *
   * @return 是否显示titleBar
   */
  boolean initializeTitleBar();

  /**
   * 获得标题栏类型，子类根据需要重写此方法展示不同样式标题栏<BR>
   *
   * @return 标题栏类型
   */
  TitleBar.TitleBarStyle getTitleBarStyle();

  /**
   * 设置内容页的背景<BR>
   *
   * @return 背景
   */
  int getContentContainerBgId();

  /**
   * 设置标题栏背景<BR>
   *
   * @param backgroundId 背景ID
   */
  void setTitleBarBackground(int backgroundId);

  /**
   * 设置标题栏是否可见<BR>
   *
   * @param isVisible 是否可见
   */
  void setTitleBarVisible(boolean isVisible);

  /**
   * 获取标题是否可见<BR>
   *
   * @return 返回标题是否可见
   */
  boolean getTitleBarVisible();

  /**
   * 设置标题栏字体颜色<BR>
   *
   * @param color 颜色id
   */
  void setTitleBarTextColor(int color);
}
