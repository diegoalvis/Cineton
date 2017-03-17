package com.diegoalvis.android.cintefon.helpers;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diegoalvis.android.cintefon.interfaces.ResultManagerInterface;

/**
 * Created by Alejandro Alvis on 3/16/17
 */
public class ResultsManager {

    //region Views
    RelativeLayout view;
    Context context;
    ProgressBar progressBar;
    TextView txtMsj;
    Snackbar snackbar;
    //endregion

    RelativeLayout.LayoutParams params;

    ResultManagerInterface resultManagerInterface;
    static ResultsManager resultsManager;

    private ResultsManager() {
    }

    public static ResultsManager getInstance(){
        if (resultsManager==null){
            resultsManager = new ResultsManager();
        }
        return resultsManager;
    }

    public void init(Context context, RelativeLayout view, ResultManagerInterface resultManagerInterface){
        this.context = context;
        this.view = view;
        this.resultManagerInterface = resultManagerInterface;
        progressBar = new ProgressBar(context);
        txtMsj = new TextView(context);
        params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
    }

    public void initLoad(){
        try{
            view.addView(progressBar,params);
            view.addView(txtMsj,params);
        }catch (Exception e){
            Log.e("Alvis -- result manager","Error al añadir views desde ResultManager: "+e.toString());
        }
        dimissSnackBar();
        progressBar.setVisibility(View.VISIBLE);
        txtMsj.setVisibility(View.GONE);
    }

    public void noItems(String msj){
        try {
            progressBar.setVisibility(View.GONE);
            txtMsj.setText(msj);
            txtMsj.setVisibility(View.VISIBLE);
            dimissSnackBar();
        }catch (Exception e){
            Log.e("Alvis -- result manager","Error en no items result manager: "+e.toString());
        }
    }

    public void ErrorLoad(String msj, String snackMsj, String action){
        try{
            progressBar.setVisibility(View.GONE);
            txtMsj.setText(msj);
            txtMsj.setVisibility(View.VISIBLE);
            snackbar = Snackbar.make(view, snackMsj, Snackbar.LENGTH_INDEFINITE).setAction(action, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resultManagerInterface.reload();
                }
            });
            snackbar.show();
        }catch (Exception e){
            Log.e("Alvis ","Error en ErrorResponse desde ResultManager: "+e.toString());
        }
    }


    public void loadOk(){
        progressBar.setVisibility(View.GONE);
        txtMsj.setText("");
        dimissSnackBar();
    }

    public void dimissSnackBar(){
        if (snackbar != null){
            snackbar.dismiss();
        }
    }


}
