package com.company.shoppingcart.dto;

import java.util.Objects;

public class Receipt {
    private float subtotal;
    private float totalTax;
    private float total;

    public Receipt() {
    }

    public Receipt(float subtotal, float totalTax, float total, float itemTax) {
        this.subtotal = subtotal;
        this.totalTax = totalTax;
        this.total = total;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(float totalTax) {
        this.totalTax = totalTax;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Objects.equals(getSubtotal(), receipt.getSubtotal()) &&
                Objects.equals(getTotalTax(), receipt.getTotalTax()) &&
                Objects.equals(getTotal(), receipt.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubtotal(), getTotalTax(), getTotal());
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "receiptSubtotal=" + subtotal +
                ", receiptTotalTax='" + totalTax + '\'' +
                ", receiptTotal'" + total +
                '}';
    }
}