package com.dl.shejimoshi.proxy;

public class WangPo implements KindWoman {

    private KindWoman kindWoman;

    public WangPo(){
        this.kindWoman=new Panjinlian();
    }

    //她可以代理任何女人
    public WangPo(KindWoman kindWoman){
        this.kindWoman=kindWoman;
    }
    @Override
    public void makeEyesWithMan() {
          this.kindWoman.happyWithMan();
    }

    @Override
    public void happyWithMan() {
          this.kindWoman.makeEyesWithMan();
    }
}
