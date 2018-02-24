/**
 * Copyright (c) 2010-2018 Mark Allen, Norbert Bartels.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.restfb.types;

import static com.restfb.util.DateUtils.toDateFromLongFormat;

import com.restfb.Facebook;
import com.restfb.JsonMapper.JsonMappingCompleted;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents the <a href="https://developers.facebook.com/docs/graph-api/reference/v2.5/open-graph-rating/"> Open Graph
 * Rating API type</a>.
 *
 * @author Alexander Nenkov
 * @since 1.20.0
 */
public class OpenGraphRating extends FacebookType {

  @Getter
  @Setter
  @Facebook("created_time")
  private String rawCreatedTime;

  /**
   * When the reviewer rated this object.
   *
   * @return When the reviewer rated this object.
   */
  @Getter
  @Setter
  private Date createdTime;

  /**
   * Was a rating included
   *
   * @return Was a rating included
   */
  @Getter
  @Setter
  @Facebook("has_rating")
  private Boolean hasRating;

  /**
   * Was there text in the rating
   *
   * @return Was there text in the rating
   */
  @Getter
  @Setter
  @Facebook("has_review")
  private Boolean hasReview;

  /**
   * Rating
   *
   * @return Rating
   */
  @Getter
  @Setter
  @Facebook("rating")
  private Integer rating;

  /**
   * Review text included in the review
   *
   * @return Review text included in the review
   */
  @Getter
  @Setter
  @Facebook("review_text")
  private String reviewText;

  /**
   * Person who rated the object
   *
   * @return Person who rated the object
   */
  @Getter
  @Setter
  @Facebook("reviewer")
  private User reviewer;

  /**
   * Open Graph story generated by the rating action
   *
   * @return Open Graph story generated by the rating action
   */
  @Getter
  @Setter
  @Facebook("open_graph_story")
  private PageRating openGraphStory;

  @JsonMappingCompleted
  void convertTime() {
    createdTime = toDateFromLongFormat(rawCreatedTime);
  }
}
