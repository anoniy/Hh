package com.kota.hh.ui.main;

import com.kota.hh.ui.base.MvpView;

import junit.framework.Test;

import java.util.List;

public interface MainMvpView extends MvpView {

    void showRibots(List<Test> ribots);

    void showRibotsEmpty();

    void showError();

}
