// pages/yan/yan.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        username: "冬城君",
        tatle: "photoshop,隐藏参考线快捷键(ctrl+;)无效?",
        time: "周五 04/20 17:49",
        xxsrc: ["../../images/xx1.png", "../../images/xx2.png"],
        colorx: ["#ff5858", "#498eff", "#ffc949", "#c68efe"],
        indexx: 0,
        delBtnWidth: 77,
        deui: 0,
        deu: null,
        hh: 0,
        animationData: {},
        program: [
            {
                color: 0,
                tatle: "view背景为何不能使用本地连接",
                time: "周五 04/20 17:49",
                indexx: 1
            },
            {
                color: 1,
                tatle: "github使用笔记",
                time: "周五 04/20 17:49",
                indexx: 0
            },
            {
                color: 2,
                tatle: "数据库原理与应用",
                time: "周五 04/20 17:49",
                indexx: 0
            },
            {
                color: 3,
                tatle: "数据结构学习笔记",
                time: "周五 04/20 17:49",
                indexx: 0
            }
        ],
        program2: [
            {
                color: 0,
                username: "呆霸王",
                tatle: "git学习指南",
                time: "周五 04/20 17:49",
                txsrc: "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJpuVY3JXdl1um0icqdBlrqw4I6goJD5E6ic2mYht5ksKtZZibTiaFohvbJOfg10icTJ8sPQJYQ4DROa9Q/132",
                indexx: 1
            },
            {
                color: 1,
                username: "冬城君",
                tatle: "从删库到跑路",
                time: "周五 04/20 17:49",
                txsrc: "https://wx.qlogo.cn/mmopen/vi_32/DDCwBmTsvaxWQeh1zZHywOZw6GIWRqEicKZiciahQe5zyoD2J6W3QewDaYtr4WMJCyogTcc9dUnWyKg1SSh7q2auw/132",
                indexx: 0
            },
            {
                color: 2,
                username: "春城君",
                tatle: "安卓常见报错",
                time: "周五 04/20 17:49",
                txsrc: "https://wx.qlogo.cn/mmopen/vi_32/KOlAbLWSrsXic5m34OrYZkK94S59eKx8HBmypHuMtxqj0EfJPMIMLmYZ8vKwMdVyZt4JpqYs0zXIB5gewOkgEZg/132",
                indexx: 0
            },
            {
                color: 3,
                username: "夏城君",
                tatle: "小程序爬坑之路",
                time: "周五 04/20 17:49",
                txsrc: "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKkM5fwGkNnTk7uDLIfFt2SgLB4xEmX3AOHEIEp2yR4U8Yl9MubRGI2DF1l1FoWoVZkpicSibhflYQw/132",
                indexx: 0
            }
        ],
        imgtype: 1,
        cimg: ["data:image/jpeg;base64,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/////////////////////////////wPoAAAAAP////////////////////////////8D6AAAAAD/////////////////////////////A+gAADhCSU0EAAAAAAAAAgACOEJJTQQCAAAAAAAKAAAAAAAAAAAAADhCSU0EMAAAAAAABQEBAQEBADhCSU0ELQAAAAAACgACAAAAAwAAAAQ4QklNBAgAAAAAABAAAAABAAACQAAAAkAAAAAAOEJJTQQeAAAAAAAEAAAAADhCSU0EGgAAAAADPwAAAAYAAAAAAAAAAAAAAIwAAALuAAAABWcqaAeYmAAtADIAAAABAAAAAAAAAAAAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAu4AAACMAAAAAAAAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAEAAAAAEAAAAAAABudWxsAAAAAgAAAAZib3VuZHNPYmpjAAAAAQAAAAAAAFJjdDEAAAAEAAAAAFRvcCBsb25nAAAAAAAAAABMZWZ0bG9uZwAAAAAAAAAAQnRvbWxvbmcAAACMAAAAAFJnaHRsb25nAAAC7gAAAAZzbGljZXNWbExzAAAAAU9iamMAAAABAAAAAAAFc2xpY2UAAAASAAAAB3NsaWNlSURsb25nAAAAAAAAAAdncm91cElEbG9uZwAAAAAAAAAGb3JpZ2luZW51bQAAAAxFU2xpY2VPcmlnaW4AAAANYXV0b0dlbmVyYXRlZAAAAABUeXBlZW51bQAAAApFU2xpY2VUeXBlAAAAAEltZyAAAAAGYm91bmRzT2JqYwAAAAEAAAAAAABSY3QxAAAABAAAAABUb3AgbG9uZwAAAAAAAAAATGVmdGxvbmcAAAAAAAAAAEJ0b21sb25nAAAAjAAAAABSZ2h0bG9uZwAAAu4AAAADdXJsVEVYVAAAAAEAAAAAAABudWxsVEVYVAAAAAEAAAAAAABNc2dlVEVYVAAAAAEAAAAAAAZhbHRUYWdURVhUAAAAAQAAAAAADmNlbGxUZXh0SXNIVE1MYm9vbAEAAAAIY2VsbFRleHRURVhUAAAAAQAAAAAACWhvcnpBbGlnbmVudW0AAAAPRVNsaWNlSG9yekFsaWduAAAAB2RlZmF1bHQAAAAJdmVydEFsaWduZW51bQAAAA9FU2xpY2VWZXJ0QWxpZ24AAAAH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AQEAAQEA/+EN0mh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8APD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNS42LWMxNDAgNzkuMTYwNDUxLCAyMDE3LzA1LzA2LTAxOjA4OjIxICAgICAgICAiPiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtbG5zOnhtcE1NPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvbW0vIiB4bWxuczpzdEV2dD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3NUeXBlL1Jlc291cmNlRXZlbnQjIiB4bWxuczpkYz0iaHR0cDovL3B1cmwub3JnL2RjL2VsZW1lbnRzLzEuMS8iIHhtbG5zOnBob3Rvc2hvcD0iaHR0cDovL25zLmFkb2JlLmNvbS9waG90b3Nob3AvMS4wLyIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ0MgKE1hY2ludG9zaCkiIHhtcDpDcmVhdGVEYXRlPSIyMDE4LTA2LTE5VDIwOjU2OjQwKzA4OjAwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDE4LTA2LTE5VDIwOjU2OjQwKzA4OjAwIiB4bXA6TW9kaWZ5RGF0ZT0iMjAxOC0wNi0xOVQyMDo1Njo0MCswODowMCIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDowOTg3NGI1NS03Nzc5LTRkMWEtOWY2Zi0xOGY0NmNmNjM3OTEiIHhtcE1NOkRvY3VtZW50SUQ9ImFkb2JlOmRvY2lkOnBob3Rvc2hvcDo5MjI0NjVjZS1jNTI1LTNkNDctOTMxYS1mOTNkMDg2NTY3Y2YiIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDozNGNmNTc0MS1iODJhLTQxMjUtYTM2OC00NjhhYTE3MzJjZGUiIGRjOmZvcm1hdD0iaW1hZ2UvanBlZyIgcGhvdG9zaG9wOkNvbG9yTW9kZT0iMyI+IDx4bXBNTTpIaXN0b3J5PiA8cmRmOlNlcT4gPHJkZjpsaSBzdEV2dDphY3Rpb249ImNyZWF0ZWQiIHN0RXZ0Omluc3RhbmNlSUQ9InhtcC5paWQ6MzRjZjU3NDEtYjgyYS00MTI1LWEzNjgtNDY4YWExNzMyY2RlIiBzdEV2dDp3aGVuPSIyMDE4LTA2LTE5VDIwOjU2OjQwKzA4OjAwIiBzdEV2dDpzb2Z0d2FyZUFnZW50PSJBZG9iZSBQaG90b3Nob3AgQ0MgKE1hY2ludG9zaCkiLz4gPHJkZjpsaSBzdEV2dDphY3Rpb249InNhdmVkIiBzdEV2dDppbnN0YW5jZUlEPSJ4bXAuaWlkOjA5ODc0YjU1LTc3NzktNGQxYS05ZjZmLTE4ZjQ2Y2Y2Mzc5MSIgc3RFdnQ6d2hlbj0iMjAxOC0wNi0xOVQyMDo1Njo0MCswODowMCIgc3RFdnQ6c29mdHdhcmVBZ2VudD0iQWRvYmUgUGhvdG9zaG9wIENDIChNYWNpbnRvc2gpIiBzdEV2dDpjaGFuZ2VkPSIvIi8+IDwvcmRmOlNlcT4gPC94bXBNTTpIaXN0b3J5PiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICA8P3hwYWNrZXQgZW5kPSJ3Ij8+/+4AIUFkb2JlAGSAAAAAAQMAEAMCAwYAAAAAAAAAAAAAAAD/2wCEAAgGBgYGBggGBggMCAcI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", "data:image/jpeg;base64,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aiAAAAACRW5tcgAAAAEAAAAAAABQYXRoAAAAAFBhdGgAAAAKdmVjdG9yTWFza25hbWUAAAABAAAAAAAATHlyIAAAAAdXBonSd+lfYgAgADIAAG9iaiAAAAACRW5tcgAAAAEAAAAAAABQYXRoAAAAAFBhdGgAAAAKdmVjdG9yTWFza25hbWUAAAABAAAAAAAATHlyIAAAAAdXBonSd+lfYgAgADMAADhCSU0EBgAAAAAABwACAQEAAQEA/+EN0mh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8APD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNS42LWMxNDAgNzkuMTYwNDUxLCAyMDE3LzA1LzA2LTAxOjA4OjIxICAgICAgICAiPiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtbG5zOnhtcE1NPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvbW0vIiB4bWxuczpzdEV2dD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3NUeXBlL1Jlc291cmNlRXZlbnQjIiB4bWxuczpkYz0iaHR0cDovL3B1cmwub3JnL2RjL2VsZW1lbnRzLzEuMS8iIHhtbG5zOnBob3Rvc2hvcD0iaHR0cDovL25zLmFkb2JlLmNvbS9waG90b3Nob3AvMS4wLyIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ0MgKE1hY2ludG9zaCkiIHhtcDpDcmVhdGVEYXRlPSIyMDE4LTA2LTE5VDIwOjU3OjE4KzA4OjAwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDE4LTA2LTE5VDIwOjU3OjE4KzA4OjAwIiB4bXA6TW9kaWZ5RGF0ZT0iMjAxOC0wNi0xOVQyMDo1NzoxOCswODowMCIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDo3MGUzNzYyMS0wNDkyLTRhZWUtOGU4MS02YTI5OTU2N2FmYmMiIHhtcE1NOkRvY3VtZW50SUQ9ImFkb2JlOmRvY2lkOnBob3Rvc2hvcDpkODI0ZDRhNy1kMDViLTE1NDMtOTk2MC0xY2IzNGI2ZjQ5MTEiIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo4ZjJjY2M4Ni03YjNjLTQ4YzQtOTRhMi1iMDYyYTgxMTAwMGQiIGRjOmZvcm1hdD0iaW1hZ2UvanBlZyIgcGhvdG9zaG9wOkNvbG9yTW9kZT0iMyI+IDx4bXBNTTpIaXN0b3J5PiA8cmRmOlNlcT4gPHJkZjpsaSBzdEV2dDphY3Rpb249ImNyZWF0ZWQiIHN0RXZ0Omluc3RhbmNlSUQ9InhtcC5paWQ6OGYyY2NjODYtN2IzYy00OGM0LTk0YTItYjA2MmE4MTEwMDBkIiBzdEV2dDp3aGVuPSIyMDE4LTA2LTE5VDIwOjU3OjE4KzA4OjAwIiBzdEV2dDpzb2Z0d2FyZUFnZW50PSJBZG9iZSBQaG90b3Nob3AgQ0MgKE1hY2ludG9zaCkiLz4gPHJkZjpsaSBzdEV2dDphY3Rpb249InNhdmVkIiBzdEV2dDppbnN0YW5jZUlEPSJ4bXAuaWlkOjcwZTM3NjIxLTA0OTItNGFlZS04ZTgxLTZhMjk5NTY3YWZiYyIgc3RFdnQ6d2hlbj0iMjAxOC0wNi0xOVQyMDo1NzoxOCswODowMCIgc3RFdnQ6c29mdHdhcmVBZ2VudD0iQWRvYmUgUGhvdG9zaG9wIENDIChNYWNpbnRvc2gpIiBzdEV2dDpjaGFuZ2VkPSIvIi8+IDwvcmRmOlNlcT4gPC94bXBNTTpIaXN0b3J5PiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg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"],
        flag: false,
        flag2: true,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

    },
    changeimg: function () {
        this.setData({
            flag: false,
            flag2: true,
            imgtype: 1
        })
    },
    changeimg2: function () {
        this.setData({
            flag: true,
            flag2: false,
            imgtype: 0
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