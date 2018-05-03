package ru.mail.park.lecture10;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SquareFragment extends Fragment {
    private  static final int ONE_SECOND = 1000;
    private  static final int QUARTER_OF_SECOND = 250;

    @BindView(R.id.root)
    View root;

    @BindView(R.id.square)
    View square;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_square, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.play)
    void onPlayClick() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(square, View.SCALE_X, 1, 1.5f);
        scaleX.setDuration(QUARTER_OF_SECOND);
        scaleX.setRepeatCount(ValueAnimator.INFINITE);

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(square, View.SCALE_Y, 1, 1.5f);
        scaleY.setDuration(QUARTER_OF_SECOND);
        scaleY.setRepeatCount(ValueAnimator.INFINITE);

        ObjectAnimator rotation = ObjectAnimator.ofFloat(square, View.ROTATION, 0, 360);
        rotation.setDuration(ONE_SECOND);
        rotation.setRepeatCount(ValueAnimator.INFINITE);

        final AnimatorSet scaleAndRotation = new AnimatorSet();
        scaleAndRotation.playTogether(scaleX, scaleY, rotation);

        ObjectAnimator motionX = ObjectAnimator.ofFloat(square, View.TRANSLATION_X,
                0, root.getWidth() - square.getWidth());
        motionX.setDuration(ONE_SECOND);
        motionX.setRepeatMode(ValueAnimator.REVERSE);
        motionX.setRepeatCount(ValueAnimator.INFINITE);

        ObjectAnimator motionY = ObjectAnimator.ofFloat(square, View.TRANSLATION_Y,
                0, root.getHeight() - square.getHeight());
        motionY.setDuration(ONE_SECOND);
        motionY.setRepeatMode(ValueAnimator.REVERSE);
        motionY.setRepeatCount(ValueAnimator.INFINITE);

        final AnimatorSet motion = new AnimatorSet();
        motion.playTogether(motionX, motionY);

        final AnimatorSet animator = new AnimatorSet();
        animator.playTogether(motion, scaleAndRotation);

        animator.start();
    }
}
