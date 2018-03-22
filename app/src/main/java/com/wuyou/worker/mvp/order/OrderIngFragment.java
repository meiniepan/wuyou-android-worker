package com.wuyou.worker.mvp.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.gs.buluo.common.widget.StatusLayout;
import com.wuyou.worker.CarefreeApplication;
import com.wuyou.worker.Constant;
import com.wuyou.worker.R;
import com.wuyou.worker.adapter.OrderIngRvAdapter;
import com.wuyou.worker.bean.entity.OrderInfoEntity;
import com.wuyou.worker.bean.entity.OrderInfoListEntity;
import com.wuyou.worker.util.MyRecyclerViewScrollListener;
import com.wuyou.worker.view.fragment.BaseFragment;
import com.wuyou.worker.view.widget.recyclerHelper.BaseQuickAdapter;
import com.wuyou.worker.view.widget.recyclerHelper.NewRefreshRecyclerView;
import com.wuyou.worker.view.widget.recyclerHelper.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by solang on 2018/1/31.
 */

public class OrderIngFragment extends BaseFragment<OrderContract.View, OrderContract.Presenter> implements OrderContract.View {


    @BindView(R.id.rv_orders)
    NewRefreshRecyclerView recyclerView;
    @BindView(R.id.sl_list_layout)
    StatusLayout statusLayout;
    @BindView(R.id.rl_to_top)
    View toTop;
    OrderIngRvAdapter adapter;
    List<OrderInfoEntity> data = new ArrayList();

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_order_before;
    }

    @Override
    protected OrderContract.Presenter getPresenter() {
        return new OrderPresenter();
    }

    @Override
    protected void bindView(Bundle savedInstanceState) {
        if (!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this);
        final MyRecyclerViewScrollListener scrollListener = new MyRecyclerViewScrollListener(getActivity(), toTop);
        adapter = new OrderIngRvAdapter(scrollListener, this, R.layout.item_order_ing, data);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
            intent.putExtra(Constant.ORDER_ID, adapter.getItem(position).id);
            intent.putExtra(Constant.DIVIDE_ORDER_FROM, 2);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        recyclerView.getRecyclerView().addOnScrollListener(scrollListener);
        recyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadMore(CarefreeApplication.getInstance().getUserInfo().getUid(), "2");
            }
        }, recyclerView.getRecyclerView());
        recyclerView.setRefreshAction(new OnRefreshListener() {
            @Override
            public void onAction() {
                scrollListener.setRefresh();
                adapter.clearData();
                fetchDatas();
            }
        });
        toTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.getRecyclerView().smoothScrollToPosition(0);
            }
        });
    }

    @Override
    public void showError(String message, int res) {
        recyclerView.setRefreshFinished();
        statusLayout.showErrorView(message);
    }

    @Override
    public void getSuccess(OrderInfoListEntity data) {
        recyclerView.setRefreshFinished();
        adapter.setNewData(data.list);
        statusLayout.showContentView();
        if (data.has_more.equals("0")) {
            adapter.loadMoreEnd(true);
        }
        if (adapter.getData().size() == 0) {
            statusLayout.showEmptyView("没有订单");
        }
    }

    @Override
    public void getMore(OrderInfoListEntity data) {
        adapter.addData(data.list);
        if (data.has_more.equals("0")) {
            adapter.loadMoreEnd(true);
        }
    }

    @Override
    public void loadMoreError(int code) {
        adapter.loadMoreFail();
    }


    @Override
    public void loadData() {
        statusLayout.showProgressView();
        fetchDatas();
        Log.e("haha", "2");
    }

    private void fetchDatas() {
        mPresenter.getOrders(CarefreeApplication.getInstance().getUserInfo().getUid(), "2");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOrderFinished(int pos) {
        adapter.remove(pos);
    }
}