package com.diegoalvis.android.cintefon.interfaces;



public interface MainPresenterInterface {

    public void onCreate(IMainView view);

    public void getImagesFromService();

    public void showList();

    public void onItemClicked(int position);

}
