# GWT-SEO
## How to use

Simply add the SeoWidget into your UiBinder. It currently supports all the following tags :

```
<!DOCTYPE ui:UiBinder SYSTEM "http://dl.google.com/gwt/DTD/xhtml.ent">
<ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
        xmlns:g="urn:import:com.google.gwt.user.client.ui"
        xmlns:seo="urn:import:com.arcbees.seo.widget">

...

<seo:SeoWidget>
    <seo:Title>
        My Title
    </seo:Title>
    <seo:Description>
        My Description
    </seo:Description>
    <seo:Keywords>
        kw1,kw2,kw3
    </seo:Keywords>
    <seo:Og>
        <seo:OgImage height="480" width="480">http://some.image.url</seo:OgImage>
        <seo:OgType typeValue="WEBSITE"/>
    </seo:Og>
    <seo:FbAppId>1234567</seo:FbAppId>
</seo:SeoWidget>

...
```

You can also use ui:msg inside the tags to use i18n messages :

```
<seo:SeoWidget>
    <seo:Title>
        <ui:msg description="SEO - MAIN - TITLE">Title</ui:msg>
    </seo:Title>
    <seo:Description>
        <ui:msg description="SEO - MAIN - DESCRIPTION">Description</ui:msg>
    </seo:Description>
    <seo:Keywords>
        <ui:msg description="SEO - MAIN - KEYWORDS">kw1,kw2,kw3</ui:msg>
    </seo:Keywords>
    <seo:Og>
        <seo:OgImage height="480" width="480">http://some.image.url</seo:OgImage>
        <seo:OgType typeValue="WEBSITE"/>
    </seo:Og>
    <seo:FbAppId>1234567</seo:FbAppId>
</seo:SeoWidget>
```

Although there are no specific `og:title` and `og:description` tags, they are copied at runtime from the `seo:Title` and ``seo:Description` tags.

## How it works
When the SeoWidget is attached to the DOM, the content is used to generate `<meta>` tags.
Those tags are then inserted into the `<head>` section. At the moment, nothing is done when the widget is detached.
A good practice is to define specific SEO components in all your views.
