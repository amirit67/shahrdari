package com.shahrdari.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import com.shahrdari.utils.backboard.performer.Performer;
import com.shahrdari.utils.backboard.rebound.SimpleSpringListener;
import com.shahrdari.utils.backboard.rebound.Spring;
import com.shahrdari.utils.backboard.rebound.SpringConfig;
import com.shahrdari.utils.backboard.rebound.SpringSystem;

//import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

public class AnimationUtils {
    /* renamed from: a */
    static SpringConfig f4086a = SpringConfig.fromOrigamiTensionAndFriction(40.0d, 7.0d);

    /* renamed from: a */
    public static void m4688a(final View view) {
        Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setInterpolator(new DecelerateInterpolator());
        alphaAnimation.setDuration(700);
        alphaAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            public void onAnimationEnd(Animation animation) {
                /*try {
                    view.post(new AnimationUtils$1$$Lambda$0(view));
                } catch (Animation animation2) {
                    ThrowableExtension.m8584a(animation2);
                }*/
            }
        });
        view.startAnimation(alphaAnimation);
    }

    /* renamed from: b */
    public static void m4692b(final View view) {
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alphaAnimation.setDuration(700);
        alphaAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                /*try {
                    view.post(new AnimationUtils$4$$Lambda$0(view));
                } catch (Animation animation2) {
                    ThrowableExtension.m8584a(animation2);
                }*/
            }
        });
        view.startAnimation(alphaAnimation);
    }

    /* renamed from: c */
    public static void m4695c(final View view) {
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alphaAnimation.setDuration(700);
        alphaAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                /*try {
                    view.post(new AnimationUtils$5$$Lambda$0(view));
                } catch (Animation animation2) {
                    ThrowableExtension.m8584a(animation2);
                }*/
            }
        });
        view.startAnimation(alphaAnimation);
    }

    /* renamed from: a */
    public static void m4689a(final TextView textView, long j, final String str) {
        final Spring b = SpringSystem.create().createSpring();
        b.setSpringConfig(f4086a);
        b.setCurrentValue(0.0d);
        b.addListener(new SimpleSpringListener() {
            /* renamed from: a */
            public void mo1096a(Spring spring) {
                super.onSpringUpdate(spring);
                TextView textView2 = textView;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(Math.round(10));
                stringBuilder.append(str);
                textView2.setText(stringBuilder.toString());
            }
        });
        b.setCurrentValue((double) j);
    }

    /* renamed from: b */
    public static void m4693b(final TextView r6, long r7, final String r9) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/173214986.run(Unknown Source)
*/
        /*
        r0 = 0;
        r1 = 1;
        r2 = r6.getText();	 Catch:{ Exception -> 0x0030 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0030 }
        r2 = java.lang.Integer.parseInt(r2);	 Catch:{ Exception -> 0x0030 }
        r3 = com.facebook.rebound.SpringSystem.m17199c();	 Catch:{ Exception -> 0x0030 }
        r3 = r3.m5578b();	 Catch:{ Exception -> 0x0030 }
        r4 = f4086a;	 Catch:{ Exception -> 0x0030 }
        r3.m5598a(r4);	 Catch:{ Exception -> 0x0030 }
        r4 = new int[r1];	 Catch:{ Exception -> 0x0030 }
        r4[r0] = r2;	 Catch:{ Exception -> 0x0030 }
        r4 = (double) r2;	 Catch:{ Exception -> 0x0030 }
        r3.m5596a(r4);	 Catch:{ Exception -> 0x0030 }
        r2 = new com.arian.mytvshow.utils.AnimationUtils$8;	 Catch:{ Exception -> 0x0030 }
        r2.<init>(r6, r9);	 Catch:{ Exception -> 0x0030 }
        r3.m5599a(r2);	 Catch:{ Exception -> 0x0030 }
        r4 = (double) r7;	 Catch:{ Exception -> 0x0030 }
        r3.m5602b(r4);	 Catch:{ Exception -> 0x0030 }
        goto L_0x0044;
    L_0x0030:
        r2 = "{0}{1}";
        r3 = 2;
        r3 = new java.lang.Object[r3];
        r7 = java.lang.Long.valueOf(r7);
        r3[r0] = r7;
        r3[r1] = r9;
        r7 = java.text.MessageFormat.format(r2, r3);
        r6.setText(r7);
    L_0x0044:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arian.mytvshow.utils.AnimationUtils.b(android.widget.TextView, long, java.lang.String):void");
    }

    /* renamed from: a */
    public static Spring m4685a(final View view, int i) {
        final Spring b = SpringSystem.create().createSpring();
        b.setSpringConfig(f4086a);
        b.setCurrentValue(500.0d);
        /* b.addListener(new SpringListener() {
         *//* renamed from: b *//*
            public void mo1097b(Spring spring) {
            }

            *//* renamed from: c *//*
            public void mo1098c(Spring spring) {
            }

            *//* renamed from: d *//*
            public void mo1099d(Spring spring) {
            }

            *//* renamed from: a *//*
            public void mo1096a(Spring spring) {
                float a = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 1.0d, 0.0d, 1.0d, 0.5d);
                ViewCompat.m1603c(view, 500.0f - Float.parseFloat(String.valueOf(spring.m5601b())));
                ViewCompat.m1597b(view, a);
            }
        });*/
        view.postDelayed(new Runnable() {
            public void run() {
                view.setVisibility(View.VISIBLE);
                b.setEndValue(0.0d);
            }
        }, (long) i);
        return b;
    }

    /* renamed from: b */
    public static Spring m4690b(final View view, int i) {
        view.setVisibility(View.INVISIBLE);
        ViewCompat.setElevation(view, 0.0f);
        final Spring b = SpringSystem.create().createSpring();
        b.setSpringConfig(f4086a);
        b.setCurrentValue(0.0d);
        b.addListener(new Performer(view, View.ALPHA));
        view.postDelayed(new Runnable() {
            public void run() {
                view.setVisibility(View.VISIBLE);
                b.setEndValue(1.0d);
            }
        }, (long) i);
        return b;
    }

    /* renamed from: c */
    public static Spring m4694c(View view, int i) {
        final Spring b = SpringSystem.create().createSpring();
        b.setSpringConfig(f4086a);
        b.setCurrentValue(1.0d);
        b.addListener(new Performer(view, View.ALPHA));
        view.postDelayed(new Runnable() {
            public void run() {
                b.setEndValue(0.0d);
            }
        }, (long) i);
        return b;
    }

    /* renamed from: d */
    public static Spring m4696d(final View view, int i) {
        view.setVisibility(View.INVISIBLE);
        final Spring b = SpringSystem.create().createSpring();
        b.setSpringConfig(f4086a);
        b.setCurrentValue(-500.0d);
        b.addListener(new Performer(view, View.TRANSLATION_Y));
        view.postDelayed(new Runnable() {
            public void run() {
                view.setVisibility(View.VISIBLE);
                b.setEndValue(0.0d);
            }
        }, (long) i);
        return b;
    }

    /* *//* renamed from: e *//*
    public static void m4697e(final View view, int i) {
        view.setVisibility(4);
        final Spring b = SpringSystem.m17199c().m5578b();
        b.m5598a(f4086a);
        b.m5596a(-500.0d);
        b.m5599a(new Performer(view, View.TRANSLATION_X));
        view.postDelayed(new Runnable() {
            public void run() {
                view.setVisibility(0);
                b.m5602b(0.0d);
            }
        }, (long) i);
    }*/
    public static void m4697e(final View view, int i) {
        view.setVisibility(View.INVISIBLE);
        final Spring b = SpringSystem.create().createSpring();
        b.setSpringConfig(f4086a);
        b.setCurrentValue(-500.0d);
        b.addListener(new Performer(view, View.TRANSLATION_X));
        view.postDelayed(new Runnable() {
            public void run() {
                view.setVisibility(View.VISIBLE);
                b.setEndValue(0.0d);
            }
        }, (long) i);
    }

    /* renamed from: f */
    public static void m4698f(final View view, int i) {
        view.setVisibility(View.INVISIBLE);
        final Spring b = SpringSystem.create().createSpring();
        b.setSpringConfig(f4086a);
        b.setCurrentValue(500.0d);
        b.addListener(new Performer(view, View.TRANSLATION_X));
        new Handler().postDelayed(new Runnable() {
            public void run() {
                view.setVisibility(View.VISIBLE);
                b.setEndValue(0.0d);
            }
        }, (long) i);
    }

    /* renamed from: g */
    public static void m4699g(final View view, int i) {
        view.setVisibility(View.INVISIBLE);
        final Spring b = SpringSystem.create().createSpring();
        b.setSpringConfig(f4086a);
        b.setCurrentValue(0.0d);
        b.addListener(new Performer(view, View.SCALE_X)).addListener(new Performer(view, View.SCALE_Y));
        view.postDelayed(new Runnable() {
            public void run() {
                view.setVisibility(View.VISIBLE);
                b.setEndValue(1.0d);
            }
        }, (long) i);
    }

    /* renamed from: a */
    public static Spring m4686a(final View view, final int i, final int i2, int i3) {
        final Spring b = SpringSystem.create().createSpring();
        b.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(15.0d, 55.0d));
        b.setCurrentValue(0.0d);
        b.addListener(new SimpleSpringListener() {
            /* renamed from: a */
            public void mo1096a(Spring spring) {
                super.onSpringUpdate(spring);
                AnimationUtils.m4691b(i, (int) spring.getCurrentValue(), view, 0, 0);
            }

            /* renamed from: b */
            public void mo1097b(Spring spring) {
                super.onSpringActivate(spring);
            }
        });
        view.postDelayed(new Runnable() {
            public void run() {
                b.setEndValue((double) i2);
            }
        }, (long) i3);
        return b;
    }

    /* renamed from: b */
    private static void m4691b(int i, int i2, View view, int i3, int i4) {
        Drawable gradientDrawable = new GradientDrawable();
        /*gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadius((float) i2);
        gradientDrawable.setStroke(i3, i4);*/
        view.setBackgroundDrawable(gradientDrawable);
    }
}
