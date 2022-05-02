package com.example.vaccheck.policy;

public class Policy {
    private boolean lnamePermission;
    private boolean fnamePermission;
    private boolean miPermission;
    private boolean dobPermission;
    private boolean patientNumPermission;
    private boolean vacDosePermission;
    private boolean productNamePermission;
    private boolean vacDatePermission;
    private boolean sitePermission;
    private boolean statusPermission;
    private boolean idimagePermission;

    public Policy(boolean lnamePermission, boolean fnamePermission, boolean miPermission,
                  boolean dobPermission, boolean patientNumPermission, boolean vacDosePermission,
                  boolean productNamePermission, boolean vacDatePermission, boolean sitePermission,
                  boolean statusPermission, boolean idimagePermission) {
        this.lnamePermission = lnamePermission;
        this.fnamePermission = fnamePermission;
        this.miPermission = miPermission;
        this.dobPermission = dobPermission;
        this.patientNumPermission = patientNumPermission;
        this.vacDosePermission = vacDosePermission;
        this.productNamePermission = productNamePermission;
        this.vacDatePermission = vacDatePermission;
        this.sitePermission = sitePermission;
        this.statusPermission = statusPermission;
        this.idimagePermission = idimagePermission;
    }

    public Policy() {
        this.lnamePermission = false;
        this.fnamePermission = false;
        this.miPermission = false;
        this.dobPermission = false;
        this.patientNumPermission = false;
        this.vacDosePermission = false;
        this.productNamePermission = false;
        this.vacDatePermission = false;
        this.sitePermission = false;
        this.statusPermission = false;
        this.idimagePermission = false;
    }

    public void setPolicy(Policy p){
        this.lnamePermission = p.isLnamePermission();
        this.fnamePermission = p.isFnamePermission();
        this.miPermission = p.isMiPermission();
        this.dobPermission = p.isDobPermission();
        this.patientNumPermission = p.isPatientNumPermission();
        this.vacDosePermission = p.isVacDosePermission();
        this.productNamePermission = p.isProductNamePermission();
        this.vacDatePermission = p.isVacDatePermission();
        this.sitePermission = p.isSitePermission();
        this.statusPermission = p.isStatusPermission();
        this.idimagePermission = p.isIdimagePermission();
    }

    public boolean isLnamePermission() {
        return lnamePermission;
    }

    public void setLnamePermission(boolean lnamePermission) {
        this.lnamePermission = lnamePermission;
    }

    public boolean isFnamePermission() {
        return fnamePermission;
    }

    public void setFnamePermission(boolean fnamePermission) {
        this.fnamePermission = fnamePermission;
    }

    public boolean isMiPermission() {
        return miPermission;
    }

    public void setMiPermission(boolean miPermission) {
        this.miPermission = miPermission;
    }

    public boolean isDobPermission() {
        return dobPermission;
    }

    public void setDobPermission(boolean dobPermission) {
        this.dobPermission = dobPermission;
    }

    public boolean isPatientNumPermission() {
        return patientNumPermission;
    }

    public void setPatientNumPermission(boolean patientNumPermission) {
        this.patientNumPermission = patientNumPermission;
    }

    public boolean isVacDosePermission() {
        return vacDosePermission;
    }

    public void setVacDosePermission(boolean vacDosePermission) {
        this.vacDosePermission = vacDosePermission;
    }

    public boolean isProductNamePermission() {
        return productNamePermission;
    }

    public void setProductNamePermission(boolean productNamePermission) {
        this.productNamePermission = productNamePermission;
    }

    public boolean isVacDatePermission() {
        return vacDatePermission;
    }

    public void setVacDatePermission(boolean vacDatePermission) {
        this.vacDatePermission = vacDatePermission;
    }

    public boolean isSitePermission() {
        return sitePermission;
    }

    public void setSitePermission(boolean sitePermission) {
        this.sitePermission = sitePermission;
    }

    public boolean isStatusPermission() {
        return statusPermission;
    }

    public void setStatusPermission(boolean statusPermission) {
        this.statusPermission = statusPermission;
    }

    public boolean isIdimagePermission() {
        return idimagePermission;
    }

    public void setIdimagePermission(boolean idimagePermission) {
        this.idimagePermission = idimagePermission;
    }

    public boolean isEmpty(){
        boolean empty = this.lnamePermission || this.fnamePermission || this.miPermission ||
                this.dobPermission || this.patientNumPermission || vacDosePermission ||
                this.productNamePermission || this.vacDatePermission || this.sitePermission ||
                this.statusPermission || this.idimagePermission;
        return !empty;
    }

    @Override
    public String toString() {
        return  "lnamePermission=" + lnamePermission +
                ", fnamePermission=" + fnamePermission +
                ", miPermission=" + miPermission +
                ", dobPermission=" + dobPermission +
                ", patientNumPermission=" + patientNumPermission +
                ", vacDosePermission=" + vacDosePermission +
                ", productNamePermission=" + productNamePermission +
                ", vacDatePermission=" + vacDatePermission +
                ", sitePermission=" + sitePermission +
                ", statusPermission=" + statusPermission +
                ", idimagePermission=" + idimagePermission;
    }
}
