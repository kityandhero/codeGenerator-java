(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[13],{mLvl:function(e,t,a){"use strict";a.r(t);var n,o,c,l=a("tUtL"),i=(a("ywkC"),a("oSez")),r=a("Xh3J"),u=a("20sq"),s=a("B6Sf"),f=a("K89m"),b=a("elvB"),g=a("ZZRV"),p=a.n(g),d=a("svyS"),h=a("uJMD"),m=a("7pZ8"),y=a.n(m),v=a("i0ey"),C=a("KNI+"),D=a("/JhO"),k=a("ShOv");function O(e){return function(){var t,a=Object(f["a"])(e);if(j()){var n=Object(f["a"])(this).constructor;t=Reflect.construct(a,arguments,n)}else t=a.apply(this,arguments);return Object(s["a"])(this,t)}}function j(){if("undefined"===typeof Reflect||!Reflect.construct)return!1;if(Reflect.construct.sham)return!1;if("function"===typeof Proxy)return!0;try{return Date.prototype.toString.call(Reflect.construct(Date,[],(function(){}))),!0}catch(e){return!1}}var L=(n=Object(d["c"])((function(e){var t=e.connectionConfig,a=e.global,n=e.loading;return{connectionConfig:t,global:a,loading:n.models.connectionConfig}})),n((c=function(e){Object(b["a"])(a,e);var t=O(a);function a(e){var n;return Object(r["a"])(this,a),n=t.call(this,e),n.componentAuthority=y.a.connectionConfig.get,n.tabList=[{key:"basicInfo",show:n.checkAuthority(y.a.connectionConfig.get),tab:"\u57fa\u672c\u4fe1\u606f"},{key:"dataBaseGeneratorConfig",show:n.checkAuthority(y.a.dataBaseGeneratorConfig.getByConnectionId),tab:"\u751f\u6210\u914d\u7f6e"},{key:"dataTable/pageList",show:n.checkAuthority(y.a.dataTable.pageList),tab:"\u6570\u636e\u5e93\u8868"}],n.getApiData=function(e){var t=e.connectionConfig.data;return t},n.checkNeedUpdate=function(e,t,a){return Object(D["a"])(n.state,e,t,a)},n.supplementLoadRequestParams=function(e){var t=e,a=n.state.connectionConfigId;return t.connectionConfigId=a,t},n.afterLoadSuccess=function(e,t,a,o){n.setState({pageName:"".concat(k["fieldData"].name.label,"\uff1a").concat(null===e?"":e.name||"")})},n.pageHeaderLogo=function(){var e=n.state.metaData;return p.a.createElement(i["a"],{size:"large",src:null===e?"":e.imageUrl||"/noImageSmall.png"})},n.pageHeaderExtraContentData=function(){var e=n.state.metaData;return{textLabel:v["a"].statusNote.label,text:null===e?"":e.statusNote,timeLabel:v["a"].createTime.label,time:null===e?null:Object(h["W"])(e.createTime)}},n.pageHeaderContentData=function(){var e=n.state.metaData,t=[];return t.push({label:k["fieldData"].connectionConfigId.label,value:null===e?"":e.connectionConfigId,canCopy:!0}),t.push({label:k["fieldData"].schema.label,value:null===e?"":e.schema,canCopy:!1}),t.push({label:k["fieldData"].connectionType.label,value:n.getDatabaseConnectionTypeName(null===e?"":"".concat(e.connectionType||"")),canCopy:!1}),t.push({label:k["fieldData"].databaseType.label,value:n.getDatabaseDatabaseTypeName(null===e?"":"".concat(e.databaseType||""))}),t.push({label:k["fieldData"].encoding.label,value:n.getDatabaseEncodingName(null===e?"":"".concat(e.encoding||""))}),t},n.state=Object(l["a"])({},n.state,{},{pageName:"".concat(k["fieldData"].name.label,"\uff1a"),loadApiPath:"connectionConfig/get",backPath:"/connectionConfig/pageList/key",connectionConfigId:null}),n}return Object(u["a"])(a,null,[{key:"getDerivedStateFromProps",value:function(e,t){return Object(h["r"])(e,t,{id:""},D["b"])}}]),a}(C["a"]),o=c))||o);t["default"]=L}}]);