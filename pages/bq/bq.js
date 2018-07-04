// pages/bq/bq.js
var app=getApp();

var GetOneNote_url = app.appServlet.servlet + "GetOneNoteServlet";
var GetOneTN_url = app.appServlet.servlet + "GetOneTNServlet";

Page({

    /**
     * 页面的初始数据
     */
    data: {
        username: "",
        note:null,
        imgurl: app.appServlet.servlet+"upload/",
        n_id:"",
        author:'',
        poster:'',
        n_page:'',
        tn_id:'',
        audiostate:false,
    },


    images: function (e) {
        var src = e.currentTarget.dataset.src;
        console.log(src);
        wx.previewImage({
            current: src,
            urls: this.data.imgalist,
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        var that=this;
        that.setData({
          n_id:options.n_id,
          tn_id:options.tn_id,
          n_page:options.n_page
        })
        this.audioCtx = wx.createAudioContext('myAudio')
    },
   
  startandpause:function(){
    if(this.data.audiostate){
      this.audioCtx.pause();
      this.setData({
        audiostate:false
      })
    }else{
      this.audioCtx.play();
      this.setData({
        audiostate: true
      })
    }
  },
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {
      var that=this;
if(that.data.n_page=='index'){
  wx.request({
    url: GetOneNote_url,
    data: {
      n_id: that.data.n_id
    },
    header: {
      "Content-Type": "json"
    },
    method: 'GET',
    success: function (res) {
      console.log(res.data)
      that.setData({
        note: res.data[0],
        author: app.appuserinfo.username,
        poster: app.appuserinfo.headimg,
        username: app.appuserinfo.username
      })
    },
    fail: function (res) { },
    complete: function (res) { },
  })
}else{
  wx.request({
    url: GetOneTN_url,
    data: {
      tn_id:that.data.tn_id
    },
    header: {
      "Content-Type": "json"
    },
    method: 'GET',
    success: function (res) {
      console.log(res.data)
      that.setData({
        note: res.data.note['0'],
        author: res.data.user['0'].nickname,
        poster: res.data.user['0'].headimg,
        username: res.data.user['0'].nickname
      })
    },
    fail: function (res) { },
    complete: function (res) { },
  })

}
    },
      swm:function(){
        var that=this;
        if(that.data.n_page=='index'){
          wx.redirectTo({
            url: '../editNote/editNote?n_id=' + that.data.n_id,
          })
        }else{
            wx.redirectTo({
              url: '../editTeamNote/editTeamNote?tn_id=' + that.data.tn_id,
            })
        }
        
      },
    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})