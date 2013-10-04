package com.example;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.TextView;
import biz.mobidev.golf.Broadcasts;
import biz.mobidev.golf.R;
import biz.mobidev.golf.controllers.GolfCenterApplication;
import biz.mobidev.golf.controllers.dialogs.BaseDialogFragment;
import biz.mobidev.golf.controllers.dialogs.CoursesSearchFragment;
import biz.mobidev.golf.controllers.dialogs.DialogUtils;
import biz.mobidev.golf.controllers.dialogs.TextInputDialog;
import biz.mobidev.golf.controllers.dialogs.timedatapicker.AbstractTimePickerDialogFragment;
import biz.mobidev.golf.controllers.dialogs.timedatapicker.DateTimePickerDialogFragment;
import biz.mobidev.golf.controllers.fragments.BaseFragment;
import biz.mobidev.golf.controllers.fragments.gpsscorecard.AddPlayersFragment;
import biz.mobidev.golf.controllers.fragments.gpsscorecard.ScorecardDropDownAdapter;
import biz.mobidev.golf.model.OnPlayersChangeListener;
import biz.mobidev.golf.model.scorecard.Contact;
import biz.mobidev.golf.model.keepingscore.GameType;
import biz.mobidev.golf.model.scorecard.Player;
import biz.mobidev.golf.network.model.dto.scorecard.search.CourseNameType;
import biz.mobidev.golf.utils.Constants;
import biz.mobidev.golf.utils.DateUtils;
import com.actionbarsherlock.internal.widget.IcsSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class BaseScorecardFragment extends Fragment {
    protected static final int MAX_PLAYERS = 3;
    protected static final String ADD_NAME_DIALOG_TAG = "add_name_dialog_tag";
    protected static final String EDIT_NAME_DIALOG_TAG = "edit_name_dialog_tag";
    protected static final String COURSE_SEARCH_DIALOG_TAG = "course_search_dialog_tag";
    protected static final String CONTACTS_DIALOG_TAG = "contacts_dialog_tag";

    protected PlayerView[] mPlayerViews = new PlayerView[MAX_PLAYERS];
    protected TextView mDatePicker;
    protected TextView mSearchClub;
    protected RadioGroup mHolesGroup;
    protected RadioGroup mTypeGroup;
    protected RadioGroup mGpsGroup;
    protected RadioGroup mShareGroup;
    protected View mStartRoundBtn;

    protected Date mDate;
    protected ArrayList<Player> mPlayers = new ArrayList<Player>();

    @Override
    public int getFragmentTitleId() {
        return R.string.gc__scorecard_fragment_title;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gc__scorecard_fragment, container, false);
        findViews(view);
        initDatePicker(view);
        initAddPlayerButtons();
        initSearchClubButton(view);
        initView();

        return view;
    }

    private void findViews(View view) {
        for (int i = 0; i < mPlayerViews.length; i++) {
            mPlayerViews[i] = new PlayerView();
        }
        mPlayerViews[0].spinner = (IcsSpinner) view.findViewById(R.id.player1);
        mPlayerViews[1].spinner = (IcsSpinner) view.findViewById(R.id.player2);
        mPlayerViews[2].spinner = (IcsSpinner) view.findViewById(R.id.player3);

        mSearchClub = (TextView) view.findViewById(R.id.searchClub);
        mStartRoundBtn = view.findViewById(R.id.gc__scorecard_start_round_button);

        mHolesGroup = (RadioGroup) view.findViewById(R.id.gc__scorecard_holes_radiogroup);
        mTypeGroup = (RadioGroup) view.findViewById(R.id.gc__scorecard_type_radiogroup);
        mGpsGroup = (RadioGroup) view.findViewById(R.id.gc__scorecard_gps_radiogroup);
        mShareGroup = (RadioGroup) view.findViewById(R.id.gc__scorecard_share_radiogroup);
    }

    protected abstract void initView();

    private void initSearchClubButton(View view) {
        mSearchClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoursesSearchFragment dialog = CoursesSearchFragment.newInstance(R.string.gc__scorecard_fragment_title);
                dialog.setCloseListener(mCourseSearchDialogListener);
                dialog.show(getFragmentManager(), COURSE_SEARCH_DIALOG_TAG);
            }
        });
    }

    private void initDialog(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }
        CoursesSearchFragment dialog = (CoursesSearchFragment) getFragmentManager().findFragmentByTag(COURSE_SEARCH_DIALOG_TAG);
        if (dialog != null) {
            dialog.setCloseListener(mCourseSearchDialogListener);
        }
    }

    private void initDatePicker(View view) {
        mDatePicker = (TextView) view.findViewById(R.id.gc__scorecard_datetime);
        mDate = DateUtils.getNearestSaturday();
        mDate.setHours(9);
        mDate.setMinutes(0);
        mDate.setSeconds(0);
        onTimeSet(mDate);

        mDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickerDialogFragment dialogFragment = DateTimePickerDialogFragment.newInstance(mDate);
                DialogUtils.showDialog(getActivity(), dialogFragment);
            }
        });
    }

    private void initAddPlayerButtons() {
        Typeface typeface = GolfCenterApplication.getInstance().getTypefaceManager().getRobotoCondensedBold();
        for (PlayerView playerView : mPlayerViews) {
            playerView.textView = (TextView) View.inflate(getActivity(), R.layout.gc__player_layout, null);
            playerView.textView.setTypeface(typeface, Typeface.BOLD);
            ScorecardDropDownAdapter adapter = new ScorecardDropDownAdapter(getActivity(), playerView.textView, playerView.spinner);
            playerView.adapter = adapter;
            playerView.spinner.setAdapter(adapter);
        }

        updatePlayersButtons();
    }

    protected void updatePlayersButtons() {
        for (int i = 0; i < mPlayers.size(); i++) {
            PlayerView playerView = mPlayerViews[i];
            updatePlayerView(playerView, i);
        }

        for (int i = mPlayers.size(); i < mPlayerViews.length; i++) {
            PlayerView playerView = mPlayerViews[i];
            updateEmptyView(playerView);
        }
    }

    protected abstract void updatePlayerView(PlayerView playerView, int idx);

    protected void updateEmptyView(PlayerView playerView) {
        String addPlayerText = getString(R.string.gc__add_player);
        playerView.textView.setText(addPlayerText);
        String[] strings = getResources().getStringArray(R.array.gc__scorecard_add_player);
        playerView.adapter.setData(Arrays.asList(strings));
        playerView.adapter.setOnItemClickListener(mAddPlayerListener);
        setBottomDrawable(playerView.textView, R.drawable.add_icon);
    }

    protected void setBottomDrawable(TextView textView, int drawableId) {
        Drawable icon = getResources().getDrawable(drawableId);
        icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
        textView.setCompoundDrawables(null, null, null, icon);
    }

    private void addFromContacts() {
        AddPlayersFragment fragment = AddPlayersFragment.newInstance(MAX_PLAYERS, mPlayers, false);
        fragment.setOnPlayersChangeListener(mOnPlayersChangeListener);
        fragment.show(getFragmentManager(), CONTACTS_DIALOG_TAG);
    }

    private void addByName() {
        TextInputDialog dialog = TextInputDialog.newInstance();
        dialog.setCloseListener(mAddNameListener);
        FragmentManager fm = getChildFragmentManager();
        dialog.show(fm, ADD_NAME_DIALOG_TAG);
    }

    private void editPlayer(int idx) {
        final Player player = mPlayers.get(idx);
        if (player.isContact()) {
            addFromContacts();
        } else {
            TextInputDialog dialog = TextInputDialog.newInstance(player.getName());
            FragmentManager fm = getChildFragmentManager();
            dialog.setCloseListener(new BaseDialogFragment.CloseListener() {
                @Override
                public void onClose(Object object) {
                    if (object == null) {
                        return;
                    }
                    player.setName((String) object);
                    updatePlayersButtons();
                }
            });
            dialog.show(fm, EDIT_NAME_DIALOG_TAG);
        }
    }

    public static void notifyPlayersUpdated() {
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(GolfCenterApplication.getInstance());
        Intent intent = new Intent(Broadcasts.PLAYERS_UPDATED.name());
        broadcastManager.sendBroadcast(intent);
    }

    protected void setEditClickListener(View view, final int idx) {
        mPlayerViews[idx].adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        editPlayer(idx);
                        break;
                    case 1:
                        mPlayers.remove(idx);
                        updatePlayersButtons();
                        break;
                }
            }
        });
    }

    @Override
    public void onTimeSet(Date date) {
        mDate = date;
        String dateString = DateUtils.getDateAsString(date, Constants.ROUND_TIME_DATE_PATTERN);
        mDatePicker.setText(dateString);
    }

    protected Integer getCourseId() {
        return (Integer) mSearchClub.getTag();
    }

    protected int getHoleNumber() {
        return mHolesGroup.getCheckedRadioButtonId() == R.id.numberOfHolesRadioButton1 ? 18 : 9;
    }

    protected GameType getGameType() {
        return mTypeGroup.getCheckedRadioButtonId() == R.id.roundFormatRadioButton1 ? GameType.STROKES : GameType.SKINS;
    }

    protected boolean isGpsEnabled() {
        return mGpsGroup.getCheckedRadioButtonId() == R.id.shotByShotRadioButton1;
    }

    protected boolean isShareEnabled() {
        return mShareGroup.getCheckedRadioButtonId() == R.id.shareRadioButton1;
    }

    protected final AdapterView.OnItemClickListener mAddPlayerListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    addFromContacts();
                    break;
                case 1:
                    addByName();
                    break;
            }
        }
    };

    private final BaseDialogFragment.CloseListener mAddNameListener = new BaseDialogFragment.CloseListener() {
        @Override
        public void onClose(Object object) {
            if (object == null) {
                return;
            }
            Player player = new Contact(null, (String) object, null);
            mPlayers.add(player);
            updatePlayersButtons();
        }
    };

    protected static class PlayerView {
        public ScorecardDropDownAdapter adapter;
        public IcsSpinner spinner;
        public TextView textView;

        PlayerView() {
        }
    }

    private final BaseDialogFragment.CloseListener mCourseSearchDialogListener = new BaseDialogFragment.CloseListener() {
        @Override
        public void onClose(Object object) {
            if (object == null) {
                return;
            }
            CourseNameType course = (CourseNameType) object;
            mSearchClub.setText(course.getFull_name());
            mSearchClub.setTag(course.getId());
        }
    };

    private final OnPlayersChangeListener mOnPlayersChangeListener = new OnPlayersChangeListener() {
        @Override
        public void onPlayersChange(List<Player> playerList) {
            mPlayers = new ArrayList<Player>(playerList);
            updatePlayersButtons();
        }
    };
}
