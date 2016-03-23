package in.srain.cube.views.ptr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public class PtrClassicFrameLayout extends PtrFrameLayout {

    private PtrClassicDefaultHeader mPtrClassicHeader;
    private PtrClassicDefaultFooter mPtrClassicFooter;

    private int mTouchSlop;
    private float mPrevX;

    public PtrClassicFrameLayout(Context context) {
        super(context);
        initViews();
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public PtrClassicFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public PtrClassicFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews();
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    /**
     * 解决图片轮播与下拉刷新的滑动事件冲突
     * @param event
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPrevX = MotionEvent.obtain(event).getX();
                break;

            case MotionEvent.ACTION_MOVE:
                final float eventX = event.getX();
                float xDiff = Math.abs(eventX - mPrevX);

                if (xDiff > mTouchSlop) {
                    return false;
                }
        }

        return super.onInterceptTouchEvent(event);
    }

    private void initViews() {
        mPtrClassicHeader = new PtrClassicDefaultHeader(getContext());
        setHeaderView(mPtrClassicHeader);
        addPtrUIHandler(mPtrClassicHeader);
        mPtrClassicFooter = new PtrClassicDefaultFooter(getContext());
        setFooterView(mPtrClassicFooter);
        addPtrUIHandler(mPtrClassicFooter);
    }

    public PtrClassicDefaultHeader getHeader() {
        return mPtrClassicHeader;
    }

    /**
     * Specify the last update time by this key string
     *
     * @param key
     */
    public void setLastUpdateTimeKey(String key) {
        setLastUpdateTimeHeaderKey(key);
        setLastUpdateTimeFooterKey(key);
    }

    public void setLastUpdateTimeHeaderKey(String key) {
        if (mPtrClassicHeader != null) {
            mPtrClassicHeader.setLastUpdateTimeKey(key);
        }
    }

    public void setLastUpdateTimeFooterKey(String key) {
        if (mPtrClassicFooter != null) {
            mPtrClassicFooter.setLastUpdateTimeKey(key);
        }
    }

    /**
     * Using an object to specify the last update time.
     *
     * @param object
     */
    public void setLastUpdateTimeRelateObject(Object object) {
        setLastUpdateTimeHeaderRelateObject(object);
        setLastUpdateTimeFooterRelateObject(object);
    }

    public void setLastUpdateTimeHeaderRelateObject(Object object) {
        if (mPtrClassicHeader != null) {
            mPtrClassicHeader.setLastUpdateTimeRelateObject(object);
        }
    }

    public void setLastUpdateTimeFooterRelateObject(Object object) {
        if (mPtrClassicFooter != null) {
            mPtrClassicFooter.setLastUpdateTimeRelateObject(object);
        }
    }
}
