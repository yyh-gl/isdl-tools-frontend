package com.isdl.spajam2019.Main.Fragment.MusicList;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isdl.spajam2019.DI.Component.DaggerActivityComponent;
import com.isdl.spajam2019.DI.Module.ActivityModule;
import com.isdl.spajam2019.Models.Music;
import com.isdl.spajam2019.R;
import com.isdl.spajam2019.Spajam2019Application;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MusicListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MusicListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicListFragment extends Fragment implements MusicListContract.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @Inject
    MusicListPresenter musicListPresenter;

    RecyclerView rv;
    MusicListAdapter adapter;
    Context context;
    MediaPlayer mediaPlayer;

    public MusicListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MusicListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MusicListFragment newInstance() {
        return new MusicListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        DaggerActivityComponent.builder()
                .appComponent(((Spajam2019Application) getActivity().getApplicationContext())
                        .getAppComponent())
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_music_list, container, false);
        context = root.getContext();


        rv = (RecyclerView) root.findViewById(R.id.musicListRecyclerView);
        musicListPresenter.getPossessedCrossMusic(2);
//        musicListPresenter.audioPlay(getActivity());


        return root;
    }


    @Override
    public void setAdapter(List<Music> possessedMusics) {
        adapter = new MusicListAdapter(possessedMusics);

        LinearLayoutManager llm = new LinearLayoutManager(context);

        rv.setHasFixedSize(true);

        rv.setLayoutManager(llm);

        rv.setAdapter(adapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
