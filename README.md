# GWT-SEO
## How to use

Simply add the SeoWidget into your UiBinder. It currently supports all the following tags :

```xml
<!DOCTYPE ui:UiBinder SYSTEM "http://dl.google.com/gwt/DTD/xhtml.ent">
<ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
             xmlns:g="urn:import:com.google.gwt.user.client.ui"
             xmlns:seo="urn:import:com.arcbees.seo.widget">
 
    <g:HTMLPanel>
        <seo:SeoWidget>
            <seo:Title>My Title</seo:Title>
            <seo:Description>My Description</seo:Description>
            <seo:Keywords>kw1,kw2,kw3</seo:Keywords>
            <seo:Og>
                <seo:OgImage height="480" width="480">http://some.image.png</seo:OgImage>
                <seo:OgType typeValue="WEBSITE"/>
            </seo:Og>
            <seo:Custom property="og:audio">http://example.com/bond/theme.mp3</seo:Custom>
            <seo:FbAppId>1234567</seo:FbAppId>
        </seo:SeoWidget>
 
        <p>This is my page, now with SEO!</p>
    </g:HTMLPanel>
</ui:UiBinder>
```

If you want to use other meta properties, you can use `<seo:Custom property="theproperty">Value</seo:Custom>` as a fallback.

### Internationalization (i18n)

You can also use ui:msg inside the tags to use i18n messages :

```xml
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

## Dynamic data
GWT-SEO can also simplify the way you handle meta properties from dynamic data

```
import com.arcbees.seo.Image;
import com.arcbees.seo.OpenGraph;
import com.arcbees.seo.SeoElements;
import com.arcbees.seo.TagsInjector;
 
// Other imports...
 
public class MyViewImpl implements MyView {
    interface Binder extends UiBinder<Widget, MyViewImpl> {}
 
    private final Widget widget;
    private final TagsInjector tagsInjector;
 
    @Inject
    MyViewImpl(
            Binder binder.
            TagsInjector tagsInjector) {
        widget = binder.createAndBindUi(this);
        this.tagsInjector = tagsInjector;
    }
 
    @Override
    public void setProduct(Product product) {
        Photo photo = product.getPhoto();
        Image image = new Image(photo.getUrl(), photo.getHeight(), photo.getWidth(), photo.getMimeType());
        OpenGraph openGraph = new OpenGraph.Builder()
                .withImage(image)
                .build();
 
        SeoElements seoElements = new SeoElements.Builder()
                .withTitle(product.getName())
                .withDescription(product.getDescription())
                .withOpenGraph(openGraph)
                .build();
        tagsInjector.inject(seoElements);
    }
 
    // ...
}
```

##Thanks to
[![Arcbees.com](http://i.imgur.com/HDf1qfq.png)](http://arcbees.com)

[![Atlassian](http://i.imgur.com/BKkj8Rg.png)](https://www.atlassian.com/)

[![IntelliJ](https://lh6.googleusercontent.com/--QIIJfKrjSk/UJJ6X-UohII/AAAAAAAAAVM/cOW7EjnH778/s800/banner_IDEA.png)](http://www.jetbrains.com/idea/index.html)
