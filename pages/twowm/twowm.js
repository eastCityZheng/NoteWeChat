// pages/twowm/twowm.js
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
      two:"",
      txurl:"",
      username:"",
      windowWidth: wx.getSystemInfoSync().windowWidth,
      windowHeight: wx.getSystemInfoSync().windowHeight,
      kuangw: wx.getSystemInfoSync().windowWidth / 375 * 274,
      kuangh: wx.getSystemInfoSync().windowHeight / 603 * 400,
      shareImgSrc:"",
      shareImgSrc1:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
    console.log("x" + this.data.windowWidth)
    console.log("y"+this.data.windowHeight)
    console.log("边框高" + this.data.windowHeight/603 * 800)

    var that = this;
    that.setData({
      two: app.appServlet.servlet + "upload/" + options.two,
      txurl: app.appuserinfo.headimg,
      username:app.appuserinfo.username
    })

    wx.showLoading({
      title: '加载中',
    })

    //下载图片
    wx.downloadFile({
      url: that.data.two,
      success: function (sres) {
        console.log(sres);
        that.data.shareImgSrc = sres.tempFilePath
        //头像
        wx.downloadFile({
          url: that.data.txurl,
          success: function (res) {
            console.log(res);
            that.data.shareImgSrc1 = res.tempFilePath
            setTimeout(function () {
              that.drawbigma();
              wx.hideLoading()
            }, 500)
          }, fail: function (fres) {
          }
        }) 
        
      }, fail: function (fres) {
      }
    })
  },
  //画白色矩形框
  drawjx: function (context) {
    var that = this;
    var w = that.data.kuangw;
    var h = that.data.kuangh;
    var x = that.data.windowWidth / 2 - (w/2);
    console.log("w"+w+"     "+"h"+h+"    "+"x"+x)
    //const ctx = wx.createCanvasContext('firstCanvas')
    context.setFillStyle('white')
    context.setShadow(0, 0, 10, '#eaeaea')  
    context.fillRect(x, 12, w, h)
    //ctx.draw()
  },
  //线
  drawxian:function(context){
    var that = this;
    var x = that.data.windowWidth / 2 - (that.data.kuangw / 2) + this.data.windowHeight / 603 *31.5;
    console.log(x)
    context.moveTo(x, this.data.windowHeight/603*290)
    context.lineTo(x + this.data.windowHeight / 603 * 51, this.data.windowHeight / 603 * 290)
    var x2 = that.data.windowWidth / 2 + this.data.windowHeight / 603*56.5
    context.moveTo(x2, this.data.windowHeight / 603 * 290)
    context.lineTo(x2 + this.data.windowHeight / 603 * 51, this.data.windowHeight / 603 * 290)
    context.setStrokeStyle('#bfbfbf')
    context.stroke()
  },
  //字
  drawtext:function(context){
    var that = this;
    var username = this.data.username;
    var x = that.data.windowWidth / 2;
    context.setFontSize(15);
    context.setFillStyle("black");
    context.setTextAlign('center')
    context.fillText(username, x, this.data.windowHeight / 603 * 340.5);
    context.stroke();
  },
  drawtext2:function(context){
    var that = this;
    var text = "扫一扫图上二维码 查看我的便签板";
    context.setFontSize(12);
    context.setFillStyle("#bebebe");
    var x = that.data.windowWidth / 2;
    context.setTextAlign('center')
    context.fillText(text, x, this.data.windowHeight / 603 * 370.5);
    context.stroke();
  },

  //二维码
  drawbigma:function(){
    var that = this;
    var w = that.data.windowWidth / 375 * 197;
    var x = that.data.windowWidth / 2 - (w / 2);
    console.log("w" + w + "     "  + "    " + "x" + x)
    var w2 = that.data.windowWidth / 375 * 53;
    var x2 = that.data.windowWidth / 2 - (w2/2);
   
    var context = wx.createCanvasContext('firstCanvas')
    var path = that.data.shareImgSrc;
    var path2 = that.data.shareImgSrc1;
    this.drawjx(context);

    context.drawImage(path, x, this.data.windowHeight / 603 * 44.5, w, w);
    context.drawImage(path2, x2, this.data.windowHeight / 603 * 265, w2, w2);

    this.drawxian(context);
    this.drawtext(context);
    this.drawtext2(context);
    context.draw();
  },
  savetap:function(){
      var that=this;
      wx.canvasToTempFilePath({
        x: that.data.windowWidth / 2 - (that.data.kuangw / 2),
        y: 12, 
        width: that.data.kuangw,
        height: that.data.kuangh,
        //原图大小
        destWidth: 274,
        destHeight: 400,
        canvasId: 'firstCanvas',
        success: function (res) {
          console.log(res.tempFilePath);

          wx.saveImageToPhotosAlbum({
            filePath: res.tempFilePath,
            success(res) {
              wx.showModal({
                title: '存图成功',
                content: '图片成功保存到相册了，去发圈噻~',
                showCancel: false,
                confirmText: '好哒',
                confirmColor: '#72B9C3',
                success: function (res) {
                  if (res.confirm) {
                    console.log('用户点击确定');
                  }
                }
              })
            }
          })
        },
        fail: function (res) {
          console.log(res)
        }
      })
      //当用户点击分享到朋友圈时，将图片保存到相册
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