(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[19],{EQ9A:function(e,t,a){"use strict";a.r(t);a("GOtF");var n,o,r,c=a("TI7E"),i=a("m25k"),u=(a("mGLM"),a("6CQv")),s=(a("EsCq"),a("/5GD")),l=(a("dvU4"),a("q69F")),d=a("Z6mO"),g=a("uf3W"),f=a("GkP1"),m=a("vVLA"),p=a("ZZRV"),b=a.n(p),v=a("9kvl"),h=a("uJMD"),y=a("qnBQ");a("Dcz3"),a("Zkg4");function C(e){var t=e.urlParams,a=t.category;return{category:a}}var k="./index.less",O=(n=Object(v["a"])((function(e){var t=e.customConfig,a=e.global,n=e.loading;return{customConfig:t,global:a,loading:n.models.customConfig}})),n((r=function(e){Object(f["a"])(a,e);var t=Object(m["a"])(a);function a(e){var n;return Object(d["a"])(this,a),n=t.call(this,e),n.doWorkWhenDidUpdate=function(e,t,a){var o=n.state.category,r=t.category;if(null!=(o||null)&&null!=(r||null)){var c=n.state,i=c.loadSuccess,u=c.dataLoading;o!==r&&(u||n.reloadData()),i||u||n.reloadData()}},n.doDidMountTask=function(){var e=n.state,t=e.category,a=e.categoryName,o=n.getCustomConfigCategoryList(),r=t,c=a;(Object(h["P"])(t)||"no"===t)&&Object(h["z"])(o)&&(o||[]).forEach((function(e,t){0===t&&(r=e.flag)})),Object(h["z"])(o)&&(o||[]).forEach((function(e){e.flag===r&&(c=e.name)})),Object(h["P"])(r)||"no"===r?v["d"].replace("/"):n.setState({category:r,categoryName:c})},n.getApiData=function(e){var t=e.customConfig.data;return t},n.supplementLoadRequestParams=function(e){var t=e,a=n.state.category;return t.category=a,t},n.afterLoadSuccess=function(e,t,a,o){n.setState({total:(t||[]).length})},n.onSwitchChange=function(e,t){var a=e.uuid;n.setBooleanValue(a,t?"1":"0")},n.setBooleanValue=function(e,t){var a=n.props.dispatch;n.setState({processing:!0}),a({type:"customConfig/set",payload:{uuid:e,value:t}}).then((function(){var e=n.getApiData(n.props),t=e.dataSuccess;t&&(requestAnimationFrame((function(){l["a"].success({placement:"bottomRight",message:"\u64cd\u4f5c\u7ed3\u679c",description:"\u6570\u636e\u5df2\u7ecf\u6210\u529f\u63d0\u4ea4\uff0c\u8bf7\u7a0d\u540e\u67e5\u770b\u8bbe\u7f6e\u662f\u5426\u6210\u529f\u3002"})})),n.setState({processing:!1}),n.reloadData())}))},n.renderTable=function(){var e=n.state,t=e.metaListData,a=e.dataLoading;return b.a.createElement(u["b"],{size:"large",className:k.articleList,rowKey:"uuid",dataSource:t,loading:a,pagination:!1,renderItem:function(e){return b.a.createElement(u["b"].Item,{key:e.uuid,actions:[b.a.createElement(s["a"],{checkedChildren:"\u5f00",unCheckedChildren:"\u5173",defaultChecked:"1"===e.value,onChange:function(t){n.onSwitchChange(e,t)}})]},b.a.createElement(u["b"].Item.Meta,{title:e.name,description:e.description}))}})},n.state=Object(i["a"])(Object(i["a"])({},n.state),{category:"",categoryName:"",pageName:"\u8bbe\u7f6e\u9879\uff1a",paramsKey:"446d0048-94e9-40ee-9b8b-7f394bc94b09",loadApiPath:"customConfig/list",loadDataAfterMount:!1,dataLoading:!1,total:0}),n}return Object(g["a"])(a,[{key:"render",value:function(){return b.a.createElement("div",{className:k.containorBox},this.renderTable(),b.a.createElement(c["a"],null))}}],[{key:"getDerivedStateFromProps",value:function(e,t){return Object(h["o"])(e,t,{category:""},C)}}]),a}(y["a"]),o=r))||o);t["default"]=O}}]);