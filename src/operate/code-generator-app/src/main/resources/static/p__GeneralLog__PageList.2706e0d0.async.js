(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[27],{khvX:function(e,a,t){"use strict";t.r(a);var n,r,l,c=t("m25k"),i=(t("pYCW"),t("T76X")),o=(t("fArn"),t("HjkH")),m=(t("0GrD"),t("0qCI")),u=(t("ZM4h"),t("RLie")),d=t("Z6mO"),g=t("GkP1"),s=t("vVLA"),f=t("ZZRV"),p=t.n(f),b=t("9kvl"),h=t("wR7S"),E=t("Gyes"),L=t("uJMD"),k=t("ydnR"),w=t("7pZ8"),v=t.n(w),O=t("i0ey"),j=t("35Mt"),F=t("lLw4"),C=t("paK7"),D=t("bDL1"),y=(n=Object(b["a"])((function(e){var a=e.generalLog,t=e.global,n=e.loading;return{generalLog:a,global:t,loading:n.models.generalLog}})),n((l=function(e){Object(g["a"])(t,e);var a=Object(s["a"])(t);function t(e){var n;return Object(d["a"])(this,t),n=a.call(this,e),n.getApiData=function(e){var a=e.generalLog.data;return a},n.goToEdit=function(e){var a=e.generalLogId,t={pathname:"/generalLog/edit/load/".concat(a,"/key/basicInfo")};b["d"].push(t)},n.renderSimpleFormInitialValues=function(){var e={};return e[O["b"].channel.name]=k["f"].flag,e},n.renderSimpleFormRow=function(){return p.a.createElement(p.a.Fragment,null,p.a.createElement(m["a"],{gutter:24},p.a.createElement(u["a"],{md:6,sm:24},n.renderSearchInput(D["fieldData"].message.label,D["fieldData"].message.name)),p.a.createElement(u["a"],{md:6,sm:24},n.renderSearchChannelSelect(!0)),n.renderSimpleFormButton()))},n.getColumnWrapper=function(){return[{dataTarget:D["fieldData"].message,align:"left",render:function(e){return p.a.createElement(p.a.Fragment,null,p.a.createElement(F["a"],{tooltip:{placement:"topLeft"},lines:1},e))}},{dataTarget:D["fieldData"].generalLogId,width:120,align:"center",render:function(e){return p.a.createElement(p.a.Fragment,null,p.a.createElement(C["a"],{tooltip:!0,lines:1,removeChildren:!0,extraContent:p.a.createElement(p.a.Fragment,null,p.a.createElement("a",{onClick:function(){Object(L["d"])(e)}},Object(L["L"])(e,"***",2,6)))},e," [\u70b9\u51fb\u590d\u5236]"))}},{dataTarget:O["b"].channel,width:160,align:"center",render:function(e,a){return p.a.createElement(p.a.Fragment,null,p.a.createElement(F["a"],{tooltip:!0,lines:1},a.channelNote))}},{dataTarget:O["b"].createTime,width:140,align:"center",sorter:!1,render:function(e){return p.a.createElement(p.a.Fragment,null,p.a.createElement(F["a"],{tooltip:!0,lines:1},""===(e||"")?"--":Object(L["m"])(Object(L["R"])(e),"YYYY-MM-DD HH:mm")))}},{dataTarget:O["b"].customOperate,width:120,fixed:"right",align:"center",render:function(e,a){return p.a.createElement(p.a.Fragment,null,p.a.createElement(i["a"].Button,{size:"small",onClick:function(){return n.goToEdit(a)},disabled:!n.checkAuthority(v.a.account.get),overlay:p.a.createElement(o["a"],{onClick:function(e){return n.handleMenuClick(e,a)}},p.a.createElement(o["a"].Item,{key:"analysis",disabled:!0},p.a.createElement(h["a"],null),"\u5206\u6790"))},p.a.createElement(E["a"],null),"\u67e5\u770b"))}}]},n.state=Object(c["a"])(Object(c["a"])({},n.state),{pageName:"\u6a21\u5757\u5217\u8868",paramsKey:"53e093b4-70d0-4a37-8eee-e8bf2ff3f687",loadApiPath:"generalLog/pageList",dateRangeFieldName:"\u751f\u6210\u65f6\u6bb5"}),n}return t}(j["a"]),r=l))||r);a["default"]=y}}]);