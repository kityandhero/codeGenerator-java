(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[24],{VZMd:function(e,a,t){"use strict";t.r(a);var n=t("m25k"),r=(t("pYCW"),t("T76X")),l=(t("fArn"),t("HjkH")),c=(t("0GrD"),t("0qCI")),i=(t("ZM4h"),t("RLie")),o=t("Z6mO"),m=t("GkP1"),d=t("vVLA"),u=t("ZZRV"),s=t.n(u),f=t("9kvl"),g={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M880 112H144c-17.7 0-32 14.3-32 32v736c0 17.7 14.3 32 32 32h736c17.7 0 32-14.3 32-32V144c0-17.7-14.3-32-32-32zm-40 728H184V184h656v656zM492 400h184c4.4 0 8-3.6 8-8v-48c0-4.4-3.6-8-8-8H492c-4.4 0-8 3.6-8 8v48c0 4.4 3.6 8 8 8zm0 144h184c4.4 0 8-3.6 8-8v-48c0-4.4-3.6-8-8-8H492c-4.4 0-8 3.6-8 8v48c0 4.4 3.6 8 8 8zm0 144h184c4.4 0 8-3.6 8-8v-48c0-4.4-3.6-8-8-8H492c-4.4 0-8 3.6-8 8v48c0 4.4 3.6 8 8 8zM340 368a40 40 0 1080 0 40 40 0 10-80 0zm0 144a40 40 0 1080 0 40 40 0 10-80 0zm0 144a40 40 0 1080 0 40 40 0 10-80 0z"}}]},name:"profile",theme:"outlined"},h=g,p=t("EcdN"),b=function(e,a){return u["createElement"](p["a"],Object.assign({},e,{ref:a,icon:h}))};b.displayName="ProfileOutlined";var v=u["forwardRef"](b),E={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M928 160H96c-17.7 0-32 14.3-32 32v640c0 17.7 14.3 32 32 32h832c17.7 0 32-14.3 32-32V192c0-17.7-14.3-32-32-32zm-792 72h752v120H136V232zm752 560H136V440h752v352zm-237-64h165c4.4 0 8-3.6 8-8v-72c0-4.4-3.6-8-8-8H651c-4.4 0-8 3.6-8 8v72c0 4.4 3.6 8 8 8z"}}]},name:"credit-card",theme:"outlined"},k=E,I=function(e,a){return u["createElement"](p["a"],Object.assign({},e,{ref:a,icon:k}))};I.displayName="CreditCardOutlined";var w,O,L,D=u["forwardRef"](I),y=t("Gyes"),M=t("uJMD"),j=t("ydnR"),z=t("7pZ8"),C=t.n(z),F=t("i0ey"),H=t("35Mt"),x=t("lLw4"),V=t("paK7"),T=t("4DO9"),R=(w=Object(f["a"])((function(e){var a=e.errorLog,t=e.global,n=e.loading;return{errorLog:a,global:t,loading:n.models.errorLog}})),w((L=function(e){Object(m["a"])(t,e);var a=Object(d["a"])(t);function t(e){var m;return Object(o["a"])(this,t),m=a.call(this,e),m.getApiData=function(e){var a=e.errorLog.data;return a},m.goToEdit=function(e){var a=e.errorLogId,t={pathname:"/errorLog/edit/load/".concat(a,"/key/basicInfo")};f["d"].push(t)},m.handleMenuClick=function(e,a){var t=e.key,n=a.errorLogId;switch(t){case"paramInfo":f["d"].push({pathname:"/errorLog/edit/load/".concat(n,"/key/paramInfo")});break;case"stackTraceInfo":f["d"].push({pathname:"/errorLog/edit/load/".concat(n,"/key/stackTraceInfo")});break;default:break}},m.renderSimpleFormInitialValues=function(){var e={};return e[F["b"].channel.name]=j["f"].flag,e},m.renderSimpleFormRow=function(){return s.a.createElement(s.a.Fragment,null,s.a.createElement(c["a"],{gutter:24},s.a.createElement(i["a"],{md:6,sm:24},m.renderSearchInput(T["fieldData"].message.label,T["fieldData"].message.name)),s.a.createElement(i["a"],{md:6,sm:24},m.renderSearchChannelSelect(!0)),m.renderSimpleFormButton()))},m.getColumn=function(){return[{title:T["fieldData"].message.label,dataIndex:T["fieldData"].message.name,align:"left",render:function(e){return s.a.createElement(s.a.Fragment,null,s.a.createElement(x["a"],{tooltip:{placement:"topLeft"},lines:1},e))}},{title:T["fieldData"].causeMessage.label,dataIndex:T["fieldData"].causeMessage.name,width:240,align:"center",render:function(e){return s.a.createElement(s.a.Fragment,null,s.a.createElement(x["a"],{tooltip:!0,lines:1},e||"--"))}},{title:T["fieldData"].scene.label,dataIndex:T["fieldData"].scene.name,width:180,align:"center",render:function(e){return s.a.createElement(s.a.Fragment,null,s.a.createElement(x["a"],{tooltip:!0,lines:1},e||"--"))}},{title:T["fieldData"].errorLogId.label,dataIndex:T["fieldData"].errorLogId.name,width:120,align:"center",render:function(e){return s.a.createElement(s.a.Fragment,null,s.a.createElement(V["a"],{tooltip:!0,lines:1,removeChildren:!0,extraContent:s.a.createElement(s.a.Fragment,null,s.a.createElement("a",{onClick:function(){Object(M["c"])(e)}},Object(M["K"])(e,"***",2,6)))},e," [\u70b9\u51fb\u590d\u5236]"))}},{title:F["b"].channel.label,dataIndex:F["b"].channel.name,width:160,align:"center",render:function(e,a){return s.a.createElement(s.a.Fragment,null,s.a.createElement(x["a"],{tooltip:!0,lines:1},a.channelNote))}},{title:F["b"].createTime.label,dataIndex:F["b"].createTime.name,width:140,align:"center",sorter:!1,render:function(e){return s.a.createElement(s.a.Fragment,null,s.a.createElement(x["a"],{tooltip:!0,lines:1},""===(e||"")?"--":Object(M["l"])(Object(M["P"])(e),"YYYY-MM-DD HH:mm")))}},{title:F["b"].customOperate.label,dataIndex:F["b"].customOperate.name,width:140,fixed:"right",align:"center",render:function(e,a){return s.a.createElement(s.a.Fragment,null,s.a.createElement(r["a"].Button,{size:"small",onClick:function(){return m.goToEdit(a)},disabled:!m.checkAuthority(C.a.account.get),overlay:s.a.createElement(l["a"],{onClick:function(e){return m.handleMenuClick(e,a)}},s.a.createElement(l["a"].Item,{key:"paramInfo"},s.a.createElement(v,null),"\u53c2\u6570\u4fe1\u606f"),s.a.createElement(l["a"].Item,{key:"stackTraceInfo"},s.a.createElement(D,null),"\u5806\u6808\u4fe1\u606f"))},s.a.createElement(y["a"],null),"\u57fa\u672c\u4fe1\u606f"))}}]},m.state=Object(n["a"])(Object(n["a"])({},m.state),{pageName:"\u6a21\u5757\u5217\u8868",paramsKey:"53e093b4-70d0-4a37-8eee-e8bf2ff3f687",loadApiPath:"errorLog/pageList",dateRangeFieldName:"\u751f\u6210\u65f6\u6bb5"}),m}return t}(H["a"]),O=L))||O);a["default"]=R}}]);