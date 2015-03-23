(function () {
    var timeout;
    
    
    // set orientation class name to root element for sidebar
    function orientationChange(){
        if(window.orientation === 0 || window.orientation === 180){
            jQuery('#bodyPage').removeClass('landscape').addClass('portrait');
          }else{
            jQuery('#bodyPage').removeClass('portrait').addClass('landscape');
          }
    };
    
    // add class on page load
    orientationChange();
    
    // add class on orientaiton change
    jQuery(window).on('orientationchange', function(event) {
        orientationChange();
    });
    
    
    try {
        // TypeHandler for custom "expandcollapse" tags:
        var expandcollapse = adf.mf.api.amx.TypeHandler.register("http://xmlns.oracle.com/custom", "expandcollapse");

        // expand-collapse toggle function
        expandcollapse.prototype._toggle = function (event) {

            var anchorElementId = event.data["anchorElement.id"],
                tImg = document.getElementById(anchorElementId),
                childRootElementId = event.data["childRootElement.id"],
                ele = document.getElementById(childRootElementId),
                $this = jQuery(ele);
            if ($this.hasClass('expanded')){
                $this.removeClass('expanded');
                tImg.className = "toggle_button_collapsed";
            }
            else {
                jQuery('.div-table-content').removeClass('expanded');
                $this.addClass('expanded');
                tImg.className = "toggle_button_expanded";
            }
        };

        // create expand collapse element markup
        expandcollapse.prototype._renderHeaderFacet = function (amxNode, rootElementWrapper, tableContent, id) {
            var headerFacetChildren = amxNode.getRenderedChildren("header");
            if (headerFacetChildren.length > 0) {

                // create Header
                var header = document.createElement("div");
                header.className = "div-table";
                rootElementWrapper.appendChild(header);

                // create anchor wrapper for header text
                var divAnchor = document.createElement("a");
                divAnchor.id = id+"_header-expand-collapse-anchor";
                divAnchor.href = "#";
                header.appendChild(divAnchor);
                divAnchor.className = "toggle_button_expanded";

                // create span with header content
                var headerText = document.createElement("span");
                headerText.className = "header_text";
                divAnchor.appendChild(headerText);


                for (var i = 0, size = headerFacetChildren.length;i < size;++i) {
                    var childElement = headerFacetChildren[i].render();
                    if (childElement)
                        divAnchor.appendChild(childElement);
                }

                // bind click event for the button
                adf.mf.api.amx.addBubbleEventListener(divAnchor, "tap", this._toggle, {
                    "childRootElement.id" : tableContent.id, "anchorElement.id" : divAnchor.id
                });
            }
        };

        /* This method renders the expandcollapse component */
        expandcollapse.prototype.render = function (amxNode, id) {
            var rootElementWrapper = document.createElement("div");
            rootElementWrapper.className = "custom-expandcollapse";
            var tableContent = document.createElement("div");
            tableContent.id = id+"_custom-expandcollapse-content";
            tableContent.className="div-table-content";

            this._renderHeaderFacet(amxNode, rootElementWrapper, tableContent, id);

            try {
                var descendants = amxNode.renderDescendants();
                for (var i = 0, size = descendants.length;i < size;++i) {
                    var elem = descendants[i];
                    tableContent.appendChild(elem);
                    //tableContent.style.display = "none";
                }
            }
            catch (problem) {
                adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "example", "render", "Problem with custom component creation: " + problem);
            }

            rootElementWrapper.appendChild(tableContent);
            return rootElementWrapper;
        };
    }
    catch (problem) {
        adf.mf.log.Framework.logp(adf.mf.log.level.SEVERE, "expandcollapse.js", "*", "Problem with custom code: " + problem);
    }
})();