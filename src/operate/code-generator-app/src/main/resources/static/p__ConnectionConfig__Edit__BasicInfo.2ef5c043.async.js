(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[14],{KhzC:function(e,a,t){e.exports={card:"antd-pro-pages-connection-config-edit-basic-info-index-card"}},wbfs:function(e,a,t){"use strict";t.r(a);var n,r,o,l=t("tUtL"),c=(t("COny"),t("qj25")),m=(t("BioO"),t("yWmS")),s=(t("6V/+"),t("cumN")),i=(t("zFcB"),t("+kI8")),d=(t("NDU/"),t("TSE5")),f=(t("W921"),t("asCL")),u=(t("BSSk"),t("Ys3+")),p=(t("R2yK"),t("Pui7")),h=t("Xh3J"),D=t("20sq"),b=t("B6Sf"),g=t("K89m"),E=t("elvB"),F=t("ZZRV"),I=t.n(F),y=t("svyS"),C=t("uJMD"),P=t("7pZ8"),T=t.n(P),S=t("i0ey"),O=t("9HkY"),j=t("/JhO"),x=t("ShOv"),w=t("KhzC"),v=t.n(w);function H(e){return function(){var a,t=Object(g["a"])(e);if(N()){var n=Object(g["a"])(this).constructor;a=Reflect.construct(t,arguments,n)}else a=t.apply(this,arguments);return Object(b["a"])(this,a)}}function N(){if("undefined"===typeof Reflect||!Reflect.construct)return!1;if(Reflect.construct.sham)return!1;if("function"===typeof Proxy)return!0;try{return Date.prototype.toString.call(Reflect.construct(Date,[],(function(){}))),!0}catch(e){return!1}}var R=(n=Object(y["c"])((function(e){var a=e.connectionConfig,t=e.global,n=e.loading;return{connectionConfig:a,global:t,loading:n.models.connectionConfig}})),n((o=function(e){Object(E["a"])(t,e);var a=H(t);function t(e){var n;return Object(h["a"])(this,t),n=a.call(this,e),n.componentAuthority=T.a.connectionConfig.get,n.buildInitialValues=function(e){var a={};return null!=e&&(a[x["fieldData"].connectionConfigId.name]=e.connectionConfigId||"",a[x["fieldData"].name.name]=e.name||"",a[x["fieldData"].connectionType.name]="".concat(e.connectionType||""),a[x["fieldData"].databaseType.name]="".concat(e.databaseType||""),a[x["fieldData"].host.name]=e.host||"",a[x["fieldData"].port.name]=e.port||"",a[x["fieldData"].userName.name]=e.userName||"",a[x["fieldData"].password.name]=e.password||"",a[x["fieldData"].schema.name]=e.schema||"",a[x["fieldData"].encoding.name]="".concat(e.encoding||""),a[x["fieldData"].sshHost.name]=e.sshHost||"",a[x["fieldData"].sshPort.name]=e.sshPort||"",a[x["fieldData"].localPort.name]=e.localPort||"",a[x["fieldData"].remotePort.name]=e.remotePort||"",a[x["fieldData"].sshUser.name]=e.sshUser||"",a[x["fieldData"].sshPassword.name]=e.sshPassword||"",a[x["fieldData"].description.name]=e.description||"",a[S["a"].createTime.name]=Object(C["q"])(e.createTime,"YYYY-MM-DD HH:mm")||"",a[S["a"].updateTime.name]=Object(C["q"])(e.updateTime,"YYYY-MM-DD HH:mm")||""),a},n.afterSetFieldsValue=function(e){n.setState({selectConnectionType:null===e?x["connectionType"].TCP_IP:e.connectionType||x["connectionType"].TCP_IP})},n.supplementSubmitRequestParams=function(e){var a=e,t=n.state,r=t.connectionConfigId,o=t.metaData,l=o.databaseGeneratorConfigId;return a.databaseGeneratorConfigId=l,a.connectionConfigId=r,a},n.afterSubmitSuccess=function(e,a,t,n,r){requestAnimationFrame((function(){p["a"].success({placement:"bottomRight",message:"\u64cd\u4f5c\u7ed3\u679c",description:"\u6570\u636e\u5df2\u7ecf\u4fdd\u5b58\u6210\u529f\uff0c\u8bf7\u8fdb\u884c\u540e\u7eed\u64cd\u4f5c\u3002"})}))},n.onConnectionTypeChange=function(e){n.setState({selectConnectionType:e?x["connectionType"].SSH:x["connectionType"].TCP_IP})},n.formContent=function(){var e=n.state,a=e.dataLoading,t=e.processing,r=e.selectConnectionType;return I.a.createElement(I.a.Fragment,null,I.a.createElement(m["a"],{title:n.renderBasicInfoTitle(),className:v.a.card,bordered:!1,extra:I.a.createElement(f["a"],{offsetTop:20},I.a.createElement("div",null,n.renderRefreshButton(),I.a.createElement(u["a"],{type:"vertical"}),n.renderSaveButton()))},I.a.createElement(s["a"],{spinning:a||t},I.a.createElement(i["a"],{gutter:24},I.a.createElement(d["a"],{lg:18,md:12,sm:24,xs:24},n.renderFormInputFormItem(x["fieldData"].name.label,x["fieldData"].name.name,!0,Object(C["f"])(x["fieldData"].name.helper))),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormDatabaseDatabaseTypeSelectFormItem())),I.a.createElement(i["a"],{gutter:24},I.a.createElement(d["a"],{lg:18,md:12,sm:24,xs:24},n.renderFormInputFormItem(x["fieldData"].host.label,x["fieldData"].host.name,!0,Object(C["f"])(x["fieldData"].host.helper))),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInputNumberFormItem(x["fieldData"].port.label,x["fieldData"].port.name,!0,Object(C["f"])(x["fieldData"].port.helper)))),I.a.createElement(i["a"],{gutter:24},I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInputFormItem(x["fieldData"].userName.label,x["fieldData"].userName.name,!0,Object(C["f"])(x["fieldData"].userName.helper))),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormPasswordFormItem(x["fieldData"].password.label,x["fieldData"].password.name,!0,Object(C["f"])(x["fieldData"].password.helper))),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInputFormItem(x["fieldData"].schema.label,x["fieldData"].schema.name,!0,Object(C["f"])(x["fieldData"].schema.helper))),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormDatabaseEncodingSelectFormItem())))),I.a.createElement(m["a"],{title:"SSH\u4fe1\u606f",className:v.a.card,bodyStyle:r!==x["connectionType"].SSH?{padding:0}:{},bordered:!1,extra:I.a.createElement(I.a.Fragment,null,I.a.createElement(c["a"],{checkedChildren:"\u5f00",unCheckedChildren:"\u5173",defaultChecked:r===x["connectionType"].SSH,onChange:function(e){n.onConnectionTypeChange(e)}}))},I.a.createElement(s["a"],{spinning:a||t},r===x["connectionType"].SSH?I.a.createElement(I.a.Fragment,null,I.a.createElement(i["a"],{gutter:24},I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInputFormItem(x["fieldData"].sshHost.label,x["fieldData"].sshHost.name,!0,Object(C["f"])(x["fieldData"].sshHost.helper))),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInputNumberFormItem(x["fieldData"].sshPort.label,x["fieldData"].sshPort.name,!0,Object(C["f"])(x["fieldData"].sshPort.helper))),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInputNumberFormItem(x["fieldData"].localPort.label,x["fieldData"].localPort.name,!0,Object(C["f"])(x["fieldData"].localPort.helper))),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInputNumberFormItem(x["fieldData"].remotePort.label,x["fieldData"].remotePort.name,!0,Object(C["f"])(x["fieldData"].remotePort.helper)))),I.a.createElement(i["a"],{gutter:24},I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInputFormItem(x["fieldData"].sshUser.label,x["fieldData"].sshUser.name,!0,Object(C["f"])(x["fieldData"].sshUser.helper))),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormPasswordFormItem(x["fieldData"].sshPassword.label,x["fieldData"].sshPassword.name,!0,Object(C["f"])(x["fieldData"].sshPassword.helper))))):null)),I.a.createElement(m["a"],{title:"\u63cf\u8ff0\u4fe1\u606f",className:v.a.card,bordered:!1},I.a.createElement(s["a"],{spinning:a||t},I.a.createElement(i["a"],{gutter:24},I.a.createElement(d["a"],{lg:24,md:24,sm:24,xs:24},n.renderFormTextAreaFormItem(x["fieldData"].description.label,x["fieldData"].description.name,!1,Object(C["f"])(x["fieldData"].description.helper)))))),I.a.createElement(m["a"],{title:"\u5176\u4ed6\u4fe1\u606f",className:v.a.card,bordered:!1},I.a.createElement(s["a"],{spinning:a||t},I.a.createElement(i["a"],{gutter:24},I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFromCreateTimeField()),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFromUpdateTimeField())))))},n.state=Object(l["a"])({},n.state,{},{loadApiPath:"connectionConfig/get",submitApiPath:"connectionConfig/updateBasicInfo",connectionConfigId:null,selectConnectionType:x["connectionType"].TCP_IP}),n}return Object(D["a"])(t,null,[{key:"getDerivedStateFromProps",value:function(e,a){return Object(C["r"])(e,a,{id:""},j["b"])}}]),t}(O["a"]),r=o))||r);a["default"]=R}}]);