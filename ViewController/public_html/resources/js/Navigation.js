(function () {
    // set orientation class name to root element for sidebar
    function orientationChange(){
        if(window.orientation === 0 || window.orientation === 180){
            $('html').removeClass('landscape').addClass('portrait');
          }else{
            $('html').removeClass('portrait').addClass('landscape');
          }
    };
    
    // add class on page load
    orientationChange();
    
    // add class on orientaiton change
    $(window).on('orientationchange', function(event) {
        orientationChange();
    });

    var leftNavigationVisible = false,
        rightNavigationVisible = false;

    leftNavigationToggleBtn = function(){
        if (leftNavigationVisible) {
            adf.mf.api.hideSpringboard();
            leftNavigationVisible = false;
            jQuery('.spring-board-link').removeClass('hide');
        }
        else {
            adf.mf.api.showSpringboard();
            leftNavigationVisible = true;
            jQuery('.spring-board-link').addClass('hide');
        }
    }

    rightNavigationToggleBtn = function(){
        if (rightNavigationVisible) {
            rightNavigationVisible = false;
            jQuery('#right-navigation').removeClass('show');
            jQuery('.rightnav-link').removeClass('hide');
        }
        else {
            rightNavigationVisible = true;
            jQuery('#right-navigation').addClass('show');
            jQuery('.rightnav-link').addClass('hide');
        }
    }
})();