(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[11],{VZMd:function(e,t,a){"use strict";var n=a("g09b");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=n(a("p0pE"));a("qVdP");var r=n(a("jsC+"));a("lUTK");var d=n(a("BvKs"));a("14J3");var u=n(a("BMrR"));a("jCWc");var o,i,f,c=n(a("kPKH")),m=n(a("2Taf")),s=n(a("l4Ni")),g=n(a("ujKo")),p=n(a("MhPg")),h=n(a("q1tI")),E=a("MuoO"),b=a("7DNP"),v=a("RBnf"),I=a("uJMD"),D=a("ydnR"),F=n(a("7pZ8")),w=a("i0ey"),k=n(a("zmXK")),C=n(a("lLw4")),L=n(a("paK7")),y=a("4DO9"),x=(o=(0,E.connect)(function(e){var t=e.errorLog,a=e.global,n=e.loading;return{errorLog:t,global:a,loading:n.models.errorLog}}),o((f=function(e){function t(e){var a;return(0,m.default)(this,t),a=(0,s.default)(this,(0,g.default)(t).call(this,e)),a.getApiData=function(e){var t=e.errorLog.data;return t},a.goToEdit=function(e){var t=a.props.dispatch,n=e.errorLogId,l={pathname:"/errorLog/edit/load/".concat(n,"/key/basicInfo")};t(b.routerRedux.push(l))},a.renderSimpleFormInitialValues=function(){var e={};return e[w.constants.channel.name]=D.unlimitedWithStringFlag.flag,e},a.renderSimpleFormRow=function(){return h.default.createElement(h.default.Fragment,null,h.default.createElement(u.default,{gutter:24},h.default.createElement(c.default,{md:6,sm:24},a.renderSearchInputFormItem(y.fieldData.message.label,y.fieldData.message.name)),h.default.createElement(c.default,{md:6,sm:24},a.renderSearchChannelFormItem(!0)),a.renderSimpleFormButton()))},a.getColumn=function(){return[{title:y.fieldData.message.label,dataIndex:y.fieldData.message.name,align:"left",render:function(e){return h.default.createElement(h.default.Fragment,null,h.default.createElement(C.default,{tooltip:!0,lines:1},e))}},{title:y.fieldData.errorLogId.label,dataIndex:y.fieldData.errorLogId.name,width:120,align:"center",render:function(e){return h.default.createElement(h.default.Fragment,null,h.default.createElement(L.default,{tooltip:!0,lines:1,removeChildren:!0,extraContent:h.default.createElement(h.default.Fragment,null,h.default.createElement("a",{onClick:function(){(0,I.copyToClipboard)(e)}},(0,I.replaceTargetText)(e,"***",2,6)))},e," [\u70b9\u51fb\u590d\u5236]"))}},{title:w.constants.channel.label,dataIndex:w.constants.channel.name,width:160,align:"center",render:function(e,t){return h.default.createElement(h.default.Fragment,null,h.default.createElement(C.default,{tooltip:!0,lines:1},t.channelNote))}},{title:w.constants.createTime.label,dataIndex:w.constants.createTime.name,width:140,align:"center",sorter:!1,render:function(e){return h.default.createElement(h.default.Fragment,null,h.default.createElement(C.default,{tooltip:!0,lines:1},""===(e||"")?"--":(0,I.formatDatetime)((0,I.toDatetime)(e),"YYYY-MM-DD HH:mm")))}},{title:w.constants.customOperate.label,dataIndex:w.constants.customOperate.name,width:120,fixed:"right",align:"center",render:function(e,t){return h.default.createElement(h.default.Fragment,null,h.default.createElement(r.default.Button,{size:"small",onClick:function(){return a.goToEdit(t)},disabled:!a.checkAuthority(F.default.account.get),overlay:h.default.createElement(d.default,{onClick:function(e){return a.handleMenuClick(e,t)}},h.default.createElement(d.default.Item,{key:"analysis",disabled:!0},h.default.createElement(v.BookOutlined,null),"\u5206\u6790"))},h.default.createElement(v.ReadOutlined,null),"\u67e5\u770b"))}}]},a.state=(0,l.default)({},a.state,{pageName:"\u6a21\u5757\u5217\u8868",paramsKey:"53e093b4-70d0-4a37-8eee-e8bf2ff3f687",loadApiPath:"errorLog/pageList",dateRangeFieldName:"\u751f\u6210\u65f6\u6bb5"}),a}return(0,p.default)(t,e),t}(k.default),i=f))||i),M=x;t.default=M}}]);