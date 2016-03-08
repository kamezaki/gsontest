package com.bookvideo.library.domain;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class ContentTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = GsonCreator.getInstance().createGson();
    }

    @Test
    public void shouldDeserializeJson() throws Exception {
        Content content;
        try (InputStream is = getClass().getResourceAsStream("/content/content_simple.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            content = gson.fromJson(reader, Content.class);
        }

        assertThat(content, is(notNullValue()));
        assertThat(content.getRestaurantId(), is("content_restaurant_id"));
        assertThat(content.getType(), is(Content.ContentType.CONTENT));
        {
            Style style = content.getStyle();
            assertThat(style, is(notNullValue()));
            assertThat(style.getBackground().getType(), is(Style.Type.WIDE));
            assertThat(style.getPadding().getHorizontalPadding(), is(10));
        }
        assertThat(content.getPages().size(), is(2));

        {
            // page 1
            Content.ContentPage page = content.getPages().get(0);
            assertThat(page.getTitle(), is("page1"));
            assertThat(page.getOrder(), is(10));
            assertThat(page.getDuration(), is(notNullValue()));
            assertThat(page.getDuration().getStart(), is("20160301"));
            assertThat(page.getDuration().getEnd(), is("20160331"));
            assertThat(page.getChildren().size(), is(1));

            {
                // child 1

                Content.ContentItem item = page.getChildren().get(0);
                assertThat(item.getType(), is(Content.ContentItemType.TEXT));
                assertThat(item.getOrder(), is(100));
                assertThat(item.getText(), is("item 1-1"));
                assertThat(item.getStyle(), is(notNullValue()));
                assertThat(item.getStyle().getFont().getName(), is("font_name"));
                assertThat(item.getStyle().getFont().getAlign(), is(Style.Align.RIGHT));
                assertThat(item.getLink(), is(notNullValue()));
                assertThat(item.getLink().getType(), is(DocumentType.CONTENT));
                assertThat(item.getLink().getData(), is("data1"));
            }
        }

        {
            // page2
            Content.ContentPage page = content.getPages().get(1);
            assertThat(page.getTitle(), is("page2"));
            assertThat(page.getDuration(), is(nullValue()));
            assertThat(page.getOrder(), is(20));

            {
                // child 1
                Content.ContentItem item = page.getChildren().get(0);
                assertThat(item.getType(), is(Content.ContentItemType.IMAGE));
                assertThat(item.getOrder(), is(200));
                assertThat(item.getText(), is("item 2-1"));
                assertThat(item.getStyle(), is(notNullValue()));
                assertThat(item.getStyle().getBackground().getImageId(), is("bg_id"));
                assertThat(item.getStyle().getBackground().getType(), is(Style.Type.CROP));
                assertThat(item.getLink(), is(notNullValue()));
                assertThat(item.getLink().getType(), is(DocumentType.MENU));
                assertThat(item.getLink().getTarget(), is("target2-1"));
            }

            {
                // child 2
                Content.ContentItem item = page.getChildren().get(1);
                assertThat(item.getType(), is(Content.ContentItemType.CONTACT));
                assertThat(item.getOrder(), is(210));
                assertThat(item.getText(), is("item 2-2"));
                assertThat(item.getStyle().getFont().getName(), is("font_name"));
                assertThat(item.getLink().getType(), is(DocumentType.DASHBOARD));
            }
        }


    }
}