(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[27],{khvX:function(e,a,t){"use strict";t.r(a);var n,l,r,i=t("m25k"),c=(t("pYCW"),t("T76X")),o=(t("fArn"),t("HjkH")),m=(t("0GrD"),t("0qCI")),d=(t("ZM4h"),t("RLie")),u=t("Z6mO"),g=t("GkP1"),s=t("vVLA"),f=t("ZZRV"),b=t.n(f),p=t("9kvl"),h=t("wR7S"),E=t("Gyes"),L=t("uJMD"),k=t("ydnR"),w=t("7pZ8"),I=t.n(w),O=t("i0ey"),v=t("35Mt"),D=t("lLw4"),j=t("paK7"),F=t("bDL1"),C=(n=Object(p["a"])((function(e){var a=e.generalLog,t=e.global,n=e.loading;return{generalLog:a,global:t,loading:n.models.generalLog}})),n((r=function(e){Object(g["a"])(t,e);var a=Object(s["a"])(t);function t(e){var n;return Object(u["a"])(this,t),n=a.call(this,e),n.getApiData=function(e){var a=e.generalLog.data;return a},n.goToEdit=function(e){var a=e.generalLogId,t={pathname:"/generalLog/edit/load/".concat(a,"/key/basicInfo")};p["d"].push(t)},n.renderSimpleFormInitialValues=function(){var e={};return e[O["b"].channel.name]=k["f"].flag,e},n.renderSimpleFormRow=function(){return b.a.createElement(b.a.Fragment,null,b.a.createElement(m["a"],{gutter:24},b.a.createElement(d["a"],{md:6,sm:24},n.renderSearchInput(F["fieldData"].message.label,F["fieldData"].message.name)),b.a.createElement(d["a"],{md:6,sm:24},n.renderSearchChannelSelect(!0)),n.renderSimpleFormButton()))},n.getColumn=function(){return[{title:F["fieldData"].message.label,dataIndex:F["fieldData"].message.name,align:"left",render:function(e){return b.a.createElement(b.a.Fragment,null,b.a.createElement(D["a"],{tooltip:{placement:"topLeft"},lines:1},e))}},{title:F["fieldData"].generalLogId.label,dataIndex:F["fieldData"].generalLogId.name,width:120,align:"center",render:function(e){return b.a.createElement(b.a.Fragment,null,b.a.createElement(j["a"],{tooltip:!0,lines:1,removeChildren:!0,extraContent:b.a.createElement(b.a.Fragment,null,b.a.createElement("a",{onClick:function(){Object(L["c"])(e)}},Object(L["K"])(e,"***",2,6)))},e," [\u70b9\u51fb\u590d\u5236]"))}},{title:O["b"].channel.label,dataIndex:O["b"].channel.name,width:160,align:"center",render:function(e,a){return b.a.createElement(b.a.Fragment,null,b.a.createElement(D["a"],{tooltip:!0,lines:1},a.channelNote))}},{title:O["b"].createTime.label,dataIndex:O["b"].createTime.name,width:140,align:"center",sorter:!1,render:function(e){return b.a.createElement(b.a.Fragment,null,b.a.createElement(D["a"],{tooltip:!0,lines:1},""===(e||"")?"--":Object(L["l"])(Object(L["P"])(e),"YYYY-MM-DD HH:mm")))}},{title:O["b"].customOperate.label,dataIndex:O["b"].customOperate.name,width:120,fixed:"right",align:"center",render:function(e,a){return b.a.createElement(b.a.Fragment,null,b.a.createElement(c["a"].Button,{size:"small",onClick:function(){return n.goToEdit(a)},disabled:!n.checkAuthority(I.a.account.get),overlay:b.a.createElement(o["a"],{onClick:function(e){return n.handleMenuClick(e,a)}},b.a.createElement(o["a"].Item,{key:"analysis",disabled:!0},b.a.createElement(h["a"],null),"\u5206\u6790"))},b.a.createElement(E["a"],null),"\u67e5\u770b"))}}]},n.state=Object(i["a"])(Object(i["a"])({},n.state),{pageName:"\u6a21\u5757\u5217\u8868",paramsKey:"53e093b4-70d0-4a37-8eee-e8bf2ff3f687",loadApiPath:"generalLog/pageList",dateRangeFieldName:"\u751f\u6210\u65f6\u6bb5"}),n}return t}(v["a"]),l=r))||l);a["default"]=C}}]);