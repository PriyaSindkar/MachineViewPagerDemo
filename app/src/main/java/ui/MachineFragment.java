package ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.priyasindkar.machineviewpagerdemo.R;

import java.util.ArrayList;
import java.util.List;


public class MachineFragment extends Fragment {

    private static final String ARG_PARAM1 = "MACHINE_ID";
    private static final String ARG_PARAM2 = "MACHINE_NAME";
    private static final String ARG_PARAM3 = "ISLISTVIEW";
    private int mParamMachineId;
    private String mParamMachineName;
    private boolean mParamIsListView = true;
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    TextView txt;

    public static MachineFragment newInstance(int param1, String param2, boolean isListView) {

        Log.e("TAG_FRAGMENT", "new instance " + param1);
        MachineFragment fragment = new MachineFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putBoolean(ARG_PARAM3, isListView);
        fragment.setArguments(args);
        return fragment;


    }

    public MachineFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParamMachineId = getArguments().getInt(ARG_PARAM1);
            mParamMachineName = getArguments().getString(ARG_PARAM2);
            mParamIsListView = getArguments().getBoolean(ARG_PARAM3);
            Log.e("TAG_FRAGMENT", "onCreate " + mParamMachineId);
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("TAG_FRAGMENT", "onResume " + mParamMachineId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("TAG_FRAGMENT", "onCreateView " + mParamMachineId);
        View convertView= inflater.inflate(R.layout.switch_list, container, false);
        //txt = (TextView) convertView.findViewById(R.id.txt);
        mRecyclerView = (RecyclerView) convertView.findViewById(R.id.recycler_view);

        final LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager1);

        List<String> feedsList = new ArrayList<>();

        // populate list machine-wise
        for(int i=0 ; i<20; i++) {
            feedsList.add("Switch "+ i+ " of machine-"+ mParamMachineId);
        }

        mAdapter = new MyAdapter(getActivity(), feedsList);
        mRecyclerView.setAdapter(mAdapter);

        if(mParamIsListView) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAdapter.setType(0);
            mAdapter.notifyDataSetChanged();
        } else {
            android.support.v7.widget.GridLayoutManager layoutManager = new android.support.v7.widget.GridLayoutManager(getActivity(), 3);
            layoutManager.supportsPredictiveItemAnimations();
            mRecyclerView.setLayoutManager(layoutManager);
            mAdapter.setType(1);
            mAdapter.notifyDataSetChanged();
        }

        return convertView;
    }


}
