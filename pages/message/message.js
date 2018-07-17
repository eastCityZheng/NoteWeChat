// pages/message/message.js
// pages/BuildNote/BuildNote.js
var app = getApp()

var InsertMessage_url = app.appServlet.servlet + "InsertMessageServlet";
var datetime = require('../../util/getDate.js');

Page({
  /**
   * 页面的初始数据
   */
  data: {
    colortype: 0,//颜色选择
    flag: true,
    textareashow: false, //设置textarea弹出时消失
    imgtop: 70,          //改变距离
    /*hasLocation: false,*/
    texta: "",
    uu_id:0
  },
  textachange: function (e) {
    this.setData({
      texta: e.detail.value
    })
  },
  //完成
  changetoXX: function () {
    var that = this;
    wx.request({
      url: InsertMessage_url,
      data: {
        u_id: app.appuserinfo.u_id,
        uu_id:that.data.uu_id,
        m_content: that.data.texta,
        m_color: that.data.colortype,
        m_time: datetime.formatTime(new Date())
      },
      header: {
        'Content-Type':'json'
      },
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: function(res) {
        console.log(res)
        if(res.data==true){
          wx.showToast({
            title: '创建成功！',
            icon: 'success',
            duration: 1000
          })
          setTimeout(function () {
            wx.navigateBack({
              url: "../Other/Other?u_id=" + that.data.uu_id
            })
          }, 1200)
        }else{
          wx.showToast({
            title: '创建失败！',
            icon: 'none',
            duration: 1000
          })
        }
      },
      fail: function(res) {},
      complete: function(res) {},
    })
  },
  //选择颜色
  s1: function (e) {
    this.setData({
      colortype: 0
    })
  },
  s2: function (e) {
    this.setData({
      colortype: 1
    })
  },
  s3: function (e) {
    this.setData({
      colortype: 2
    })
  },
  s4: function (e) {
    this.setData({
      colortype: 3
    })
  },
  s5: function (e) {
    this.setData({
      colortype: 4
    })
  },
  s6: function (e) {
    this.setData({
      colortype: 5
    })
  },
 
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    that.setData({
      uu_id:options.uu_id
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

