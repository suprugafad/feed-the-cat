package by.bsuir.feed_the_cat;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

public class SliderItemFragment extends Fragment {

    private static final String ARG_POSITION = "slider-position";

    // prepare all title ids arrays
    @StringRes
    private static final int[] PAGE_TITLES =
            new int[] { R.string.feed_slider, R.string.high_scores_slider, R.string.friends_slider, R.string.love_slider };

    // prepare all subtitle ids arrays
    @StringRes
    private static final int[] PAGE_TEXT =
            new int[] {
                    R.string.feed_slider_text, R.string.high_scores_slider_text, R.string.friends_slider_text, R.string.love_slider_text
            };

    // prepare all subtitle images arrays
    @StringRes
    private static final int[] PAGE_IMAGE =
            new int[] {
                    R.drawable.feed, R.drawable.ic_reward, R.drawable.friends, R.drawable.love
            };

    // prepare all background images arrays
    @StringRes
    private static final int[] BG_IMAGE = new int[] {
//            R.drawable.daisy, R.drawable.daisy, R.drawable.daisy, R.drawable.daisy
            R.drawable.ic_bg_blue, R.drawable.ic_bg_green, R.drawable.ic_bg_purple,
            R.drawable.ic_bg_red
    };

    private int position;

    public SliderItemFragment() {}

    public static SliderItemFragment newInstance(int position) {
        SliderItemFragment fragment = new SliderItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_slider_item, container, false);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // set page background
        view.setBackground(requireActivity().getDrawable(BG_IMAGE[position]));
        TextView title = view.findViewById(R.id.textView);
        TextView titleText = view.findViewById(R.id.textView2);
        ImageView imageView = view.findViewById(R.id.imageView);
        // set page title
        title.setText(PAGE_TITLES[position]);
        // set page sub title text
        titleText.setText(PAGE_TEXT[position]);
        // set page image
        imageView.setImageResource(PAGE_IMAGE[position]);
    }
}