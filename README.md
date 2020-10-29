# PopReader
 Application to extract data from .pop files from valve's tf2 game
 
 # Planned features
 * GUI
 * File importer
 * Read templates from within pop (currently requires any files with templates to go in the template folder)
 * Time based stats (Estimated total bots including missions and related)

# Version 0.1.0 (Initial commit)
### Known Issues
* Keywords are case sensitive and can be false-positivly identified if they are located within another keyword (ie Health and useBossHealth) however most of these have been accounted for currently.
* Files with custom templates inside need to be copied into the templates folder to be recognized (non-issue since only 1 official file uses custom templates however trying to read a custom file with custom templates will not work).

### Current stats output
* Total bot health per population (excluding missions)
* Total bot health per population (excluding missions AND support)
* Total tank health per population
* Total bot count per population (excluding missions)
* * Per wave breakdown of the above stat
* Total bot count per population (excluding missions AND support)
* * Per wave breakdown of the above stat
