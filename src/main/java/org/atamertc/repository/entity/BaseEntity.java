package org.atamertc.repository.entity;

public class BaseEntity {
    int state;
    long olusturmaTarihi;
    long guncellemeTarihi;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getOlusturmaTarihi() {
        return olusturmaTarihi;
    }

    public void setOlusturmaTarihi(long olusturmaTarihi) {
        this.olusturmaTarihi = olusturmaTarihi;
    }

    public long getGuncellemeTarihi() {
        return guncellemeTarihi;
    }

    public void setGuncellemeTarihi(long guncellemeTarihi) {
        this.guncellemeTarihi = guncellemeTarihi;
    }
}
