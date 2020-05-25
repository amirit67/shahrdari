package com.shahrdari.interactor;

import androidx.annotation.UiThread;

import com.shahrdari.models.ProductItem;

import java.util.List;

@UiThread
public interface ProductsView extends BaseView {
    void onGetProducts(List<ProductItem> productItems);

}