(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[19],{EQ9A:function(t,e,a){"use strict";a.r(e);a("dcnU");var n,o,r,c=a("Q66/"),i=a("tUtL"),u=(a("YG2z"),a("kPZG")),s=(a("COny"),a("qj25")),l=(a("R2yK"),a("Pui7")),d=a("Xh3J"),f=a("20sq"),g=a("B6Sf"),m=a("K89m"),p=a("elvB"),y=a("ZZRV"),h=a.n(y),v=a("svyS"),b=a("9kvl"),C=a("uJMD"),j=a("t6Xd");a("RrRN"),a("V/D1");function D(t){var e=t.urlParams,a=e.category;return{category:a}}function O(t){return function(){var e,a=Object(m["a"])(t);if(S()){var n=Object(m["a"])(this).constructor;e=Reflect.construct(a,arguments,n)}else e=a.apply(this,arguments);return Object(g["a"])(this,e)}}function S(){if("undefined"===typeof Reflect||!Reflect.construct)return!1;if(Reflect.construct.sham)return!1;if("function"===typeof Proxy)return!0;try{return Date.prototype.toString.call(Reflect.construct(Date,[],(function(){}))),!0}catch(t){return!1}}var k="./index.less",E=(n=Object(v["c"])((function(t){var e=t.customConfig,a=t.global,n=t.loading;return{customConfig:e,global:a,loading:n.models.customConfig}})),n((r=function(t){Object(p["a"])(a,t);var e=O(a);function a(t){var n;return Object(d["a"])(this,a),n=e.call(this,t),n.doWorkWhenDidUpdate=function(t,e,a){var o=n.state.category,r=e.category;if(null!=(o||null)&&null!=(r||null)){var c=n.state,i=c.loadSuccess,u=c.dataLoading;o!==r&&(u||n.reloadData()),i||u||n.reloadData()}},n.doDidMountTask=function(){var t=n.state,e=t.category,a=t.categoryName,o=n.getCustomConfigCategoryList(),r=e,c=a;(Object(C["V"])(e)||"no"===e)&&Object(C["E"])(o)&&(o||[]).forEach((function(t,e){0===e&&(r=t.flag)})),Object(C["E"])(o)&&(o||[]).forEach((function(t){t.flag===r&&(c=t.name)})),Object(C["V"])(r)||"no"===r?b["d"].replace("/"):n.setState({category:r,categoryName:c})},n.getApiData=function(t){var e=t.customConfig.data;return e},n.supplementLoadRequestParams=function(t){var e=t,a=n.state.category;return e.category=a,e},n.afterLoadSuccess=function(t,e,a,o){n.setState({total:(e||[]).length})},n.onSwitchChange=function(t,e){var a=t.uuid;n.setBooleanValue(a,e?"1":"0")},n.setBooleanValue=function(t,e){var a=n.props.dispatch;n.setState({processing:!0}),a({type:"customConfig/set",payload:{uuid:t,value:e}}).then((function(){var t=n.getApiData(n.props),e=t.dataSuccess;e&&(requestAnimationFrame((function(){l["a"].success({placement:"bottomRight",message:"\u64cd\u4f5c\u7ed3\u679c",description:"\u6570\u636e\u5df2\u7ecf\u6210\u529f\u63d0\u4ea4\uff0c\u8bf7\u7a0d\u540e\u67e5\u770b\u8bbe\u7f6e\u662f\u5426\u6210\u529f\u3002"})})),n.setState({processing:!1}),n.reloadData())}))},n.renderTable=function(){var t=n.state,e=t.metaListData,a=t.dataLoading;return h.a.createElement(u["a"],{size:"large",className:k.articleList,rowKey:"uuid",dataSource:e,loading:a,pagination:!1,renderItem:function(t){return h.a.createElement(u["a"].Item,{key:t.uuid,actions:[h.a.createElement(s["a"],{checkedChildren:"\u5f00",unCheckedChildren:"\u5173",defaultChecked:"1"===t.value,onChange:function(e){n.onSwitchChange(t,e)}})]},h.a.createElement(u["a"].Item.Meta,{title:t.name,description:t.description}))}})},n.state=Object(i["a"])({},n.state,{},{category:"",categoryName:"",pageName:"\u8bbe\u7f6e\u9879\uff1a",paramsKey:"446d0048-94e9-40ee-9b8b-7f394bc94b09",loadApiPath:"customConfig/list",loadDataAfterMount:!1,dataLoading:!1,total:0}),n}return Object(f["a"])(a,[{key:"render",value:function(){return h.a.createElement("div",{className:k.containorBox},this.renderTable(),h.a.createElement(c["a"],null))}}],[{key:"getDerivedStateFromProps",value:function(t,e){return Object(C["r"])(t,e,{category:""},D)}}]),a}(j["a"]),o=r))||o);e["default"]=E}}]);