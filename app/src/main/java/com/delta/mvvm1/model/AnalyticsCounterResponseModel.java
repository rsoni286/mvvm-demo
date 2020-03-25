package com.delta.mvvm1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnalyticsCounterResponseModel {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Data {

        @SerializedName("total_orders")
        @Expose
        private Integer totalOrders;
        @SerializedName("total_zero_orders")
        @Expose
        private Integer totalZeroOrders;
        @SerializedName("total_order_value")
        @Expose
        private String totalOrderValue;
        @SerializedName("total_pending_collection_amount")
        @Expose
        private String totalPendingCollectionAmount;
        @SerializedName("total_cleared_collection_amount")
        @Expose
        private String totalClearedCollectionAmount;
        @SerializedName("total_expense")
        @Expose
        private String totalExpense;
        @SerializedName("present_days")
        @Expose
        private Integer presentDays;
        @SerializedName("leaves")
        @Expose
        private Integer leaves;
        @SerializedName("total_target_clients")
        @Expose
        private Integer totalTargetClients;
        @SerializedName("scheduled_effective_calls")
        @Expose
        private Integer scheduledEffectiveCalls;
        @SerializedName("unscheduled_effective_calls")
        @Expose
        private Integer unscheduledEffectiveCalls;

        public Integer getTotalOrders() {
            return totalOrders;
        }

        public void setTotalOrders(Integer totalOrders) {
            this.totalOrders = totalOrders;
        }

        public Integer getTotalZeroOrders() {
            return totalZeroOrders;
        }

        public void setTotalZeroOrders(Integer totalZeroOrders) {
            this.totalZeroOrders = totalZeroOrders;
        }

        public String getTotalOrderValue() {
            return totalOrderValue;
        }

        public void setTotalOrderValue(String totalOrderValue) {
            this.totalOrderValue = totalOrderValue;
        }

        public String getTotalPendingCollectionAmount() {
            return totalPendingCollectionAmount;
        }

        public void setTotalPendingCollectionAmount(String totalPendingCollectionAmount) {
            this.totalPendingCollectionAmount = totalPendingCollectionAmount;
        }

        public String getTotalClearedCollectionAmount() {
            return totalClearedCollectionAmount;
        }

        public void setTotalClearedCollectionAmount(String totalClearedCollectionAmount) {
            this.totalClearedCollectionAmount = totalClearedCollectionAmount;
        }

        public String getTotalExpense() {
            return totalExpense;
        }

        public void setTotalExpense(String totalExpense) {
            this.totalExpense = totalExpense;
        }

        public Integer getPresentDays() {
            return presentDays;
        }

        public void setPresentDays(Integer presentDays) {
            this.presentDays = presentDays;
        }

        public Integer getLeaves() {
            return leaves;
        }

        public void setLeaves(Integer leaves) {
            this.leaves = leaves;
        }

        public Integer getTotalTargetClients() {
            return totalTargetClients;
        }

        public void setTotalTargetClients(Integer totalTargetClients) {
            this.totalTargetClients = totalTargetClients;
        }

        public Integer getScheduledEffectiveCalls() {
            return scheduledEffectiveCalls;
        }

        public void setScheduledEffectiveCalls(Integer scheduledEffectiveCalls) {
            this.scheduledEffectiveCalls = scheduledEffectiveCalls;
        }

        public Integer getUnscheduledEffectiveCalls() {
            return unscheduledEffectiveCalls;
        }

        public void setUnscheduledEffectiveCalls(Integer unscheduledEffectiveCalls) {
            this.unscheduledEffectiveCalls = unscheduledEffectiveCalls;
        }

    }

}
