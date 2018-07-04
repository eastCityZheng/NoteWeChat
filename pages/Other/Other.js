// pages/SeeOtherNote/SeeOtherNote.js
var app=getApp()
var GetOneUserPublicNote_url = app.appServlet.servlet + "GetOneUserPublicNoteServlet";
var GetOneUser_url = app.appServlet.servlet + "GetOneUserServlet";

Page({

    /**
     * 页面的初始数据
     */
    data: {
        username: "",
        u_id:'',
        program: null
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
      console.log(options)
      this.setData({
        u_id:options.u_id,
        username:options.nickname
      })
    },
    tobq:function(e){
      wx.navigateTo({
        url: '../bq/bq?n_id='+e.currentTarget.dataset.n_id+"&&n_page=index",
      })
    },
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {
      var that=this;
      wx.request({
        url: GetOneUserPublicNote_url,
        data: {
          u_id:that.data.u_id
        },
        header: {
          "Content-Type":"json"
        },
        method: 'GET',
        dataType: 'json',
        responseType: 'text',
        success: function(res) {
          console.log(res.data)
          that.setData({
            program:res.data
          })
        },
        fail: function(res) {},
        complete: function(res) {},
      })
      wx.request({
        url: GetOneUser_url,
        data: {
          u_id:that.data.u_id
        },
        header: {
          "Content-Type":"json"
        },
        method: 'GET',
        dataType: 'json',
        responseType: 'text',
        success: function(res) {
          console.log(res.data)
          that.setData({
            username:res.data['0'].nickname
          })
        },
        fail: function(res) {},
        complete: function(res) {},
      })

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