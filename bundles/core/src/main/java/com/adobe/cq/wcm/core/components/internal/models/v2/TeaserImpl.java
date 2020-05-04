/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ Copyright 2019 Adobe
 ~
 ~ Licensed under the Apache License, Version 2.0 (the "License");
 ~ you may not use this file except in compliance with the License.
 ~ You may obtain a copy of the License at
 ~
 ~     http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing, software
 ~ distributed under the License is distributed on an "AS IS" BASIS,
 ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ~ See the License for the specific language governing permissions and
 ~ limitations under the License.
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
package com.adobe.cq.wcm.core.components.internal.models.v2;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.wcm.core.components.commons.link.Link;
import com.adobe.cq.wcm.core.components.models.ListItem;
import com.adobe.cq.wcm.core.components.models.Teaser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Model(adaptables = SlingHttpServletRequest.class, adapters = {Teaser.class, ComponentExporter.class}, resourceType = TeaserImpl.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME , extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class TeaserImpl extends com.adobe.cq.wcm.core.components.internal.models.v1.TeaserImpl {

    public final static String RESOURCE_TYPE = "core/wcm/components/teaser/v2/teaser";

    @Override
    public @NotNull Link getLink() {
        return link;
    }

    @Override
    @JsonIgnore
    @Deprecated
    public String getLinkURL() {
        return super.getLinkURL();
    }

    protected ListItem newAction(Resource actionRes) {
        return new Action(actionRes, getId());
    }

    @JsonIgnoreProperties({"path", "description", "lastModified", "name"})
    public class Action extends com.adobe.cq.wcm.core.components.internal.models.v1.TeaserImpl.Action {
        
        public Action(Resource actionRes, String parentId) {
            super(actionRes, parentId);
        }

        @Override
        public @NotNull Link getLink() {
            return actionLink;
        }

        @Nullable
        @Override
        @JsonIgnore
        @Deprecated
        public String getURL() {
            return super.getURL();
        }

    }

}
