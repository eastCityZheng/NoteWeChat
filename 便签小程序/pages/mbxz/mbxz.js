// pages/mbxz/mbxz.js
var app=getApp();
var GetOneUserPersonalList_url= app.appServlet.servlet + "GetOneUserPersonalListServlet";
Page({

    /**
     * 页面的初始数据
     */
    data: {
        image:'',
        text: "我的便签",
        image2: "/images/dgdg.png",
        program: [
            {
                image:"/images/tp.png",
                text:"我的便签",
            },
            {
                image: "/images/tp.png",
                text: "我的便签",
            },
            {
                image: "/images/tp.png",
                text: "我的便签",
            },
            {
                image: "/images/tp.png",
                text: "我的便签",
            },
        ]
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

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
        url: GetOneUserPersonalList_url,
        data: {
          u_id:app.appuserinfo.u_id
        },
        header: {
          "Content-Type":"json"
        },
        method: 'GET',
        success: function(res) {
          console.log(res)
          that.setData({
            program:res.data,
            image: app.appuserinfo.headimg
          })
        },
        fail: function(res) {},
        complete: function(res) {},
      })
    },
    toOther:function(e){
      console.log(e)
      wx.redirectTo({
        url: '../Other/Other?u_id=' + e.currentTarget.dataset.u_id + "&&nickname=" + e.currentTarget.dataset.nickname,
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