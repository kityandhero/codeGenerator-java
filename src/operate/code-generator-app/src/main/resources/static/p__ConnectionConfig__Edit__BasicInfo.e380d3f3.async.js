(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[14],{KhzC:function(e,a,t){e.exports={card:"antd-pro-pages-connection-config-edit-basic-info-index-card"}},wbfs:function(e,a,t){"use strict";t.r(a);var n,r,o,l=t("XEnU"),m=(t("BoS7"),t("Sdc0")),s=(t("IzEo"),t("bx4M")),i=(t("T2oS"),t("W9HT")),c=(t("14J3"),t("BMrR")),d=(t("jCWc"),t("kPKH")),f=(t("1YHl"),t("VNzZ")),p=(t("/zsF"),t("PArb")),u=(t("/xke"),t("TeRw")),D=t("XKWP"),g=t("N7Kx"),h=t("bvJU"),b=t("XPR9"),E=t("kFHX"),F=t("q1tI"),I=t.n(F),P=t("9kvl"),T=t("uJMD"),C=t("7pZ8"),y=t.n(C),x=t("i0ey"),S=t("9HkY"),w=t("/JhO"),H=t("ShOv"),N=t("KhzC"),v=t.n(N),O=(n=Object(P["a"])((function(e){var a=e.connectionConfig,t=e.global,n=e.loading;return{connectionConfig:a,global:t,loading:n.models.connectionConfig}})),n((o=function(e){function a(e){var t;return Object(D["a"])(this,a),t=Object(h["a"])(this,Object(b["a"])(a).call(this,e)),t.componentAuthority=y.a.connectionConfig.get,t.buildInitialValues=function(e){var a={};return null!=e&&(a[H["fieldData"].connectionConfigId.name]=e.connectionConfigId||"",a[H["fieldData"].name.name]=e.name||"",a[H["fieldData"].connectionType.name]="".concat(e.connectionType||""),a[H["fieldData"].databaseType.name]="".concat(e.databaseType||""),a[H["fieldData"].host.name]=e.host||"",a[H["fieldData"].port.name]=e.port||"",a[H["fieldData"].userName.name]=e.userName||"",a[H["fieldData"].password.name]=e.password||"",a[H["fieldData"].schema.name]=e.schema||"",a[H["fieldData"].encoding.name]="".concat(e.encoding||""),a[H["fieldData"].sshHost.name]=e.sshHost||"",a[H["fieldData"].sshPort.name]=e.sshPort||"",a[H["fieldData"].localPort.name]=e.localPort||"",a[H["fieldData"].remotePort.name]=e.remotePort||"",a[H["fieldData"].sshUser.name]=e.sshUser||"",a[H["fieldData"].sshPassword.name]=e.sshPassword||"",a[H["fieldData"].description.name]=e.description||"",a[x["a"].createTime.name]=Object(T["q"])(e.createTime,"YYYY-MM-DD HH:mm")||"",a[x["a"].updateTime.name]=Object(T["q"])(e.updateTime,"YYYY-MM-DD HH:mm")||""),a},t.afterSetFieldsValue=function(e){t.setState({selectConnectionType:null===e?H["connectionType"].TCP_IP:e.connectionType||H["connectionType"].TCP_IP})},t.supplementSubmitRequestParams=function(e){var a=e,n=t.state,r=n.connectionConfigId,o=n.metaData,l=o.databaseGeneratorConfigId;return a.databaseGeneratorConfigId=l,a.connectionConfigId=r,a},t.afterSubmitSuccess=function(e,a,t,n,r){requestAnimationFrame((function(){u["a"].success({placement:"bottomRight",message:"\u64cd\u4f5c\u7ed3\u679c",description:"\u6570\u636e\u5df2\u7ecf\u4fdd\u5b58\u6210\u529f\uff0c\u8bf7\u8fdb\u884c\u540e\u7eed\u64cd\u4f5c\u3002"})}))},t.onConnectionTypeChange=function(e){t.setState({selectConnectionType:e?H["connectionType"].SSH:H["connectionType"].TCP_IP})},t.formContent=function(){var e=t.state,a=e.dataLoading,n=e.processing,r=e.selectConnectionType;return I.a.createElement(I.a.Fragment,null,I.a.createElement(s["a"],{title:t.renderBasicInfoTitle(),className:v.a.card,bordered:!1,extra:I.a.createElement(f["a"],{offsetTop:20},I.a.createElement("div",null,t.renderRefreshButton(),I.a.createElement(p["a"],{type:"vertical"}),t.renderSaveButton()))},I.a.createElement(i["a"],{spinning:a||n},I.a.createElement(c["a"],{gutter:24},I.a.createElement(d["a"],{lg:18,md:12,sm:24,xs:24},t.renderFormInputFormItem(H["fieldData"].name.label,H["fieldData"].name.name,!0,H["fieldData"].name.helper)),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormDatabaseDatabaseTypeSelectFormItem())),I.a.createElement(c["a"],{gutter:24},I.a.createElement(d["a"],{lg:18,md:12,sm:24,xs:24},t.renderFormInputFormItem(H["fieldData"].host.label,H["fieldData"].host.name,!0,H["fieldData"].host.helper)),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormInputNumberFormItem(H["fieldData"].port.label,H["fieldData"].port.name,!0,H["fieldData"].port.helper))),I.a.createElement(c["a"],{gutter:24},I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormInputFormItem(H["fieldData"].userName.label,H["fieldData"].userName.name,!0,H["fieldData"].userName.helper)),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormPasswordFormItem(H["fieldData"].password.label,H["fieldData"].password.name,!0,H["fieldData"].password.helper)),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormInputFormItem(H["fieldData"].schema.label,H["fieldData"].schema.name,!0,H["fieldData"].schema.helper)),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormDatabaseEncodingSelectFormItem())))),I.a.createElement(s["a"],{title:"SSH\u4fe1\u606f",className:v.a.card,bodyStyle:r!==H["connectionType"].SSH?{padding:0}:{},bordered:!1,extra:I.a.createElement(I.a.Fragment,null,I.a.createElement(m["a"],{checkedChildren:"\u5f00",unCheckedChildren:"\u5173",defaultChecked:r===H["connectionType"].SSH,onChange:function(e){t.onConnectionTypeChange(e)}}))},I.a.createElement(i["a"],{spinning:a||n},r===H["connectionType"].SSH?I.a.createElement(I.a.Fragment,null,I.a.createElement(c["a"],{gutter:24},I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormInputFormItem(H["fieldData"].sshHost.label,H["fieldData"].sshHost.name,!0,H["fieldData"].sshHost.helper)),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormInputNumberFormItem(H["fieldData"].sshPort.label,H["fieldData"].sshPort.name,!0,H["fieldData"].sshPort.helper)),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormInputNumberFormItem(H["fieldData"].localPort.label,H["fieldData"].localPort.name,!0,H["fieldData"].localPort.helper)),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormInputNumberFormItem(H["fieldData"].remotePort.label,H["fieldData"].remotePort.name,!0,H["fieldData"].remotePort.helper))),I.a.createElement(c["a"],{gutter:24},I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormInputFormItem(H["fieldData"].sshUser.label,H["fieldData"].sshUser.name,!0,H["fieldData"].sshUser.helper)),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormPasswordFormItem(H["fieldData"].sshPassword.label,H["fieldData"].sshPassword.name,!0,H["fieldData"].sshPassword.helper)))):null)),I.a.createElement(s["a"],{title:"\u63cf\u8ff0\u4fe1\u606f",className:v.a.card,bordered:!1},I.a.createElement(i["a"],{spinning:a||n},I.a.createElement(c["a"],{gutter:24},I.a.createElement(d["a"],{lg:24,md:24,sm:24,xs:24},t.renderFormTextAreaFormItem(H["fieldData"].description.label,H["fieldData"].description.name,!1,H["fieldData"].description.helper))))),I.a.createElement(s["a"],{title:"\u5176\u4ed6\u4fe1\u606f",className:v.a.card,bordered:!1},I.a.createElement(i["a"],{spinning:a||n},I.a.createElement(c["a"],{gutter:24},I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFromCreateTimeField()),I.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFromUpdateTimeField())))))},t.state=Object(l["a"])({},t.state,{},{loadApiPath:"connectionConfig/get",submitApiPath:"connectionConfig/updateBasicInfo",connectionConfigId:null,selectConnectionType:H["connectionType"].TCP_IP}),t}return Object(E["a"])(a,e),Object(g["a"])(a,null,[{key:"getDerivedStateFromProps",value:function(e,a){return Object(T["r"])(e,a,{id:""},w["b"])}}]),a}(S["a"]),r=o))||r);a["default"]=O}}]);