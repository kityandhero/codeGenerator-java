(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[12],{WmNs:function(e,a,t){e.exports={card:"antd-pro-pages-connection-config-add-index-card"}},dvFV:function(e,a,t){"use strict";t.r(a);var n,r,l,o=t("k1fw"),c=(t("BoS7"),t("Sdc0")),m=(t("IzEo"),t("bx4M")),s=(t("T2oS"),t("W9HT")),i=(t("14J3"),t("BMrR")),d=(t("jCWc"),t("kPKH")),p=(t("1YHl"),t("VNzZ")),f=(t("/xke"),t("TeRw")),u=t("fWQN"),g=t("mtLc"),D=t("yKVA"),h=t("879j"),b=t("q1tI"),E=t.n(b),y=t("9kvl"),C=t("uJMD"),F=t("i0ey"),T=t("0c1e"),S=t("7pZ8"),x=t.n(S),P=t("eVFP"),I=t("ShOv"),w=t("WmNs"),v=t.n(w),N=(n=Object(y["a"])((function(e){var a=e.connectionConfig,t=e.global,n=e.loading;return{connectionConfig:a,global:t,loading:n.models.connectionConfig}})),n((l=function(e){Object(D["a"])(t,e);var a=Object(h["a"])(t);function t(e){var n;return Object(u["a"])(this,t),n=a.call(this,e),n.componentAuthority=x.a.connectionConfig.add,n.buildInitialValues=function(){var e=n.props.global,a=e.generatorTypeList||[],t={};return Object(C["y"])(a)&&a.length>0&&(t[T["customFieldCollection"].generatorType.name]="".concat(a[0].flag)),t[I["fieldData"].port.name]=0,t[F["c"].createTime.name]=Object(C["l"])(new Date,"YYYY-MM-DD HH:mm"),t},n.getApiData=function(e){var a=e.connectionConfig.data;return a},n.supplementSubmitRequestParams=function(e){var a=e,t=n.state.selectConnectionType;return a.connectionType=t,a},n.afterSubmitSuccess=function(e,a,t,n,r){requestAnimationFrame((function(){f["a"].success({placement:"bottomRight",message:"\u64cd\u4f5c\u7ed3\u679c",description:"\u6570\u636e\u5df2\u7ecf\u4fdd\u5b58\u6210\u529f\uff0c\u8bf7\u8fdb\u884c\u4e0b\u4e00\u6b65\u64cd\u4f5c\u3002"})}));var l=e.connectionConfigId,o={pathname:"/connectionConfig/edit/load/".concat(l,"/key/basicInfo")};y["d"].replace(o)},n.onConnectionTypeChange=function(e){n.setState({selectConnectionType:e?I["connectionType"].SSH:I["connectionType"].TCP_IP})},n.formContent=function(){var e=n.state,a=e.processing,t=e.selectConnectionType;return E.a.createElement(E.a.Fragment,null,E.a.createElement(m["a"],{title:"\u57fa\u672c\u4fe1\u606f",className:v.a.card,bordered:!1,extra:E.a.createElement(p["a"],{offsetTop:20},E.a.createElement("div",null,n.renderSaveButton()))},E.a.createElement(s["a"],{delay:500,spinning:a},E.a.createElement(i["a"],{gutter:24},E.a.createElement(d["a"],{lg:12,md:12,sm:24,xs:24},n.renderFormInput(I["fieldData"].name.label,I["fieldData"].name.name,!0,I["fieldData"].name.helper)),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormGeneratorTypeSelect()),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormDatabaseDatabaseTypeSelectSelect())),E.a.createElement(i["a"],{gutter:24},E.a.createElement(d["a"],{lg:18,md:12,sm:24,xs:24},n.renderFormInput(I["fieldData"].host.label,I["fieldData"].host.name,!0,I["fieldData"].host.helper)),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInputNumber(I["fieldData"].port.label,I["fieldData"].port.name,!1,I["fieldData"].port.helper))),E.a.createElement(i["a"],{gutter:24},E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInput(I["fieldData"].userName.label,I["fieldData"].userName.name,!0,I["fieldData"].userName.helper)),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormPassword(I["fieldData"].password.label,I["fieldData"].password.name,!0,I["fieldData"].password.helper)),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInput(I["fieldData"].schema.label,I["fieldData"].schema.name,!0,I["fieldData"].schema.helper)),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormDatabaseEncodingSelect())))),E.a.createElement(m["a"],{title:"SSH\u4fe1\u606f",className:v.a.card,bodyStyle:t!==I["connectionType"].SSH?{padding:0}:{},bordered:!1,extra:E.a.createElement(E.a.Fragment,null,E.a.createElement(c["a"],{checkedChildren:"\u5f00",unCheckedChildren:"\u5173",defaultChecked:t===I["connectionType"].SSH,onChange:function(e){n.onConnectionTypeChange(e)}}))},E.a.createElement(s["a"],{delay:500,spinning:a},t===I["connectionType"].SSH?E.a.createElement(E.a.Fragment,null,E.a.createElement(i["a"],{gutter:24},E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInput(I["fieldData"].sshHost.label,I["fieldData"].sshHost.name,!0,I["fieldData"].sshHost.helper)),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInputNumber(I["fieldData"].sshPort.label,I["fieldData"].sshPort.name,!0,I["fieldData"].sshPort.helper)),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInputNumber(I["fieldData"].localPort.label,I["fieldData"].localPort.name,!0,I["fieldData"].localPort.helper)),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInputNumber(I["fieldData"].remotePort.label,I["fieldData"].remotePort.name,!0,I["fieldData"].remotePort.helper))),E.a.createElement(i["a"],{gutter:24},E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInput(I["fieldData"].sshUser.label,I["fieldData"].sshUser.name,!0,I["fieldData"].sshUser.helper)),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormPassword(I["fieldData"].sshPassword.label,I["fieldData"].sshPassword.name,!0,I["fieldData"].sshPassword.helper)))):null)),E.a.createElement(m["a"],{title:"\u63cf\u8ff0\u4fe1\u606f",className:v.a.card,bordered:!1},E.a.createElement(s["a"],{delay:500,spinning:a},E.a.createElement(i["a"],{gutter:24},E.a.createElement(d["a"],{lg:24,md:24,sm:24,xs:24},n.renderFormTextArea(I["fieldData"].description.label,I["fieldData"].description.name,!1,I["fieldData"].description.helper))))),E.a.createElement(m["a"],{title:"\u5176\u4ed6\u4fe1\u606f",className:v.a.card,bordered:!1},E.a.createElement(s["a"],{delay:500,spinning:a},E.a.createElement(i["a"],{gutter:24},E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFromCreateTimeField())))))},n.state=Object(o["a"])(Object(o["a"])({},n.state),{pageName:"\u589e\u52a0\u6570\u636e\u8fde\u63a5",submitApiPath:"connectionConfig/addBasicInfo",selectConnectionType:I["connectionType"].TCP_IP}),n}return Object(g["a"])(t,null,[{key:"getDerivedStateFromProps",value:function(e,a){return Object(C["n"])(e,a)}}]),t}(P["a"]),r=l))||r);a["default"]=N}}]);