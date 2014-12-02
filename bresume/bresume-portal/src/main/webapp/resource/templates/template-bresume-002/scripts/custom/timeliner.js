/*
 * Timeliner.js
 * @version     1.6.1
 * @copyright   Tarek Anandan (http://www.technotarek.com)
 */
define(['jquery'], function($) {;
    (function($) {
        $.timeliner = function(options) {
            if ($.timeliners == null) {
                $.timeliners = {
                    options: []
                };
                $.timeliners.options.push(options);
            } else {
                $.timeliners.options.push(options);
            }
            $(document).ready(function() {
                for (var i = 0; i < $.timeliners.options.length; i++) {
                    startTimeliner($.timeliners.options[i]);
                }
            });
        }

        function startTimeliner(options) {
            var settings = {
                timelineContainer: options['timelineContainer'] || '#timelineContainer', // value: selector of the main element holding the timeline's content, default to #timelineContainer
                startState: options['startState'] || 'closed', // value: closed | open,
                // default to closed; sets whether the timeline is
                // initially collapsed or fully expanded
                startOpen: options['startOpen'] || [], // value: array of IDs of
                // single timelineEvents, default to empty; sets
                // the minor events that you want to display open
                // by default on page load
                baseSpeed: options['baseSpeed'] || 200, // value: numeric, default to
                // 200; sets the base speed for animation of the
                // event marker
                speed: options['speed'] || 1, // value: numeric, defalut to 4; a
                // multiplier applied to the base speed that sets
                // the speed at which an event's conents are
                // displayed and hidden
                expandAllText: options['expandAllText'] || '+ expand all', // value:
                // string, sets the text of the expandAll selector
                // after the timeline is fully collapsed
                collapseAllText: options['collapseAllText'] || '- collapse all' // // value:
                // string, sets the text of the expandAll selector
                // after the timeline is fully expanded
            };

            function openEvent(eventHeading, eventBody) {
                $(eventHeading).removeClass('closed').addClass('open');
                $(eventBody).show(settings.speed * settings.baseSpeed);
            }

            function closeEvent(eventHeading, eventBody) {
                $(eventHeading).removeClass('open').addClass('closed');
                $(eventBody).hide(settings.speed * settings.baseSpeed);
            }


            if ($(settings.timelineContainer).data('started')) {
                return; // we already initialized this timelineContainer
            } else {
                $(settings.timelineContainer).data('started', true);

                // Minor Event Click
                $(settings.timelineContainer).on("click", ".timelineMinor dt", function() {
                    var $this = $(this);
                    $this.parents("dl").toggleClass('selected');
                });



                // Major Marker Click
                $(settings.timelineContainer).on("click", ".timelineMajorMarker", function() {
                    var $this = $(this);

                    var total = $this.parents('.timelineMajor').find('.timelineMinor');
                    var totalCount = total.length;
                    var selectedCount = $this.parents('.timelineMajor').find('.selected').length;

                    if(selectedCount == 0 || selectedCount < totalCount){ //open all
                        total.addClass('selected');
                    }else{ // close all
                        total.removeClass('selected');
                    }
                });



                // All Markers/Events
                $(settings.timelineContainer + " " + ".expandAll").click(function() {
                    if ($(this).hasClass('expanded')) {
                        closeEvent($(this).parents(settings.timelineContainer).find("dt a", "dl.timelineMinor"), $(this).parents(settings.timelineContainer).find(".timelineEvent"));
                        $(this).removeClass('expanded').html(settings.expandAllText);
                    } else {
                        openEvent($(this).parents(settings.timelineContainer).find("dt a", "dl.timelineMinor"), $(this).parents(settings.timelineContainer).find(".timelineEvent"));
                        $(this).addClass('expanded').html(settings.collapseAllText);
                    }
                });
            }
        };
    })($);
});