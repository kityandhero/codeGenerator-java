(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[13],{khvX:function(e,t,a){"use strict";var n=a("mZ4U");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=n(a("mK77"));a("gYhO");var r=n(a("3BvE"));a("Q1sh");var d=n(a("UF7A"));a("Yg2Q");var u=n(a("PO0K"));a("0pur");var i,o,c,f=n(a("SP3O")),m=n(a("43Yg")),s=n(a("scpF")),g=n(a("O/V9")),p=n(a("8aBX")),h=n(a("+ufk")),E=a("LneV"),b=a("DWJA"),v=a("z6Ls"),F=a("uJMD"),D=a("ydnR"),I=n(a("7pZ8")),L=a("i0ey"),k=n(a("zmXK")),w=n(a("lLw4")),y=n(a("paK7")),x=a("bDL1"),C=(i=(0,E.connect)(function(e){var t=e.generalLog,a=e.global,n=e.loading;return{generalLog:t,global:a,loading:n.models.generalLog}}),i((c=function(e){function t(e){var a;return(0,m.default)(this,t),a=(0,s.default)(this,(0,g.default)(t).call(this,e)),a.getApiData=function(e){var t=e.generalLog.data;return t},a.goToEdit=function(e){var t=a.props.dispatch,n=e.generalLogId,l={pathname:"/generalLog/edit/load/".concat(n,"/key/basicInfo")};t(b.routerRedux.push(l))},a.renderSimpleFormInitialValues=function(){var e={};return e[L.constants.channel.name]=D.unlimitedWithStringFlag.flag,e},a.renderSimpleFormRow=function(){return h.default.createElement(h.default.Fragment,null,h.default.createElement(u.default,{gutter:24},h.default.createElement(f.default,{md:6,sm:24},a.renderSearchInputFormItem(x.fieldData.message.label,x.fieldData.message.name)),h.default.createElement(f.default,{md:6,sm:24},a.renderSearchChannelFormItem(!0)),a.renderSimpleFormButton()))},a.getColumn=function(){return[{title:x.fieldData.message.label,dataIndex:x.fieldData.message.name,align:"left",render:function(e){return h.default.createElement(h.default.Fragment,null,h.default.createElement(w.default,{tooltip:!0,lines:1},e))}},{title:x.fieldData.generalLogId.label,dataIndex:x.fieldData.generalLogId.name,width:120,align:"center",render:function(e){return h.default.createElement(h.default.Fragment,null,h.default.createElement(y.default,{tooltip:!0,lines:1,removeChildren:!0,extraContent:h.default.createElement(h.default.Fragment,null,h.default.createElement("a",{onClick:function(){(0,F.copyToClipboard)(e)}},(0,F.replaceTargetText)(e,"***",2,6)))},e," [\u70b9\u51fb\u590d\u5236]"))}},{title:L.constants.channel.label,dataIndex:L.constants.channel.name,width:160,align:"center",render:function(e,t){return h.default.createElement(h.default.Fragment,null,h.default.createElement(w.default,{tooltip:!0,lines:1},t.channelNote))}},{title:L.constants.createTime.label,dataIndex:L.constants.createTime.name,width:140,align:"center",sorter:!1,render:function(e){return h.default.createElement(h.default.Fragment,null,h.default.createElement(w.default,{tooltip:!0,lines:1},""===(e||"")?"--":(0,F.formatDatetime)((0,F.toDatetime)(e),"YYYY-MM-DD HH:mm")))}},{title:L.constants.customOperate.label,dataIndex:L.constants.customOperate.name,width:120,fixed:"right",align:"center",render:function(e,t){return h.default.createElement(h.default.Fragment,null,h.default.createElement(r.default.Button,{size:"small",onClick:function(){return a.goToEdit(t)},disabled:!a.checkAuthority(I.default.account.get),overlay:h.default.createElement(d.default,{onClick:function(e){return a.handleMenuClick(e,t)}},h.default.createElement(d.default.Item,{key:"analysis",disabled:!0},h.default.createElement(v.BookOutlined,null),"\u5206\u6790"))},h.default.createElement(v.ReadOutlined,null),"\u67e5\u770b"))}}]},a.state=(0,l.default)({},a.state,{pageName:"\u6a21\u5757\u5217\u8868",paramsKey:"53e093b4-70d0-4a37-8eee-e8bf2ff3f687",loadApiPath:"generalLog/pageList",dateRangeFieldName:"\u751f\u6210\u65f6\u6bb5"}),a}return(0,p.default)(t,e),t}(k.default),o=c))||o),O=C;t.default=O}}]);