package com.mina_mikhail.mvvmsampleapp.ui.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.List;

/*
 * *
 *  * Created by Mina Mikhail on 29/04/2019
 *  * Copyright (c) 2019 . All rights reserved.
 * *
 */

public abstract class BaseAdapter<T>
    extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private Context context;
  private List<T> items;

  public abstract RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType);

  public abstract void onBindData(RecyclerView.ViewHolder holder, int position);

  public BaseAdapter(Context context, List<T> items) {
    this.context = context;
    this.items = items;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return setViewHolder(parent, viewType);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    onBindData(holder, position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public int getItemViewType(int position) {
    return position;
  }

  @Override
  public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
    super.onViewDetachedFromWindow(holder);
  }

  public void replaceItems(List<T> items) {
    this.items.clear();
    this.items.addAll(items);
    this.notifyDataSetChanged();
  }

  public void addItems(List<T> items) {
    this.items.addAll(items);
    this.notifyDataSetChanged();
  }

  public T getItem(int position) {
    return items.get(position);
  }
}